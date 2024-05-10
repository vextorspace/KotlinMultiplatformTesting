package notifications

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import platform.PlatformIdentifier
import java.awt.SystemTray
import java.awt.TrayIcon
import kotlin.test.Test

class NotifySendCreatedTest {

    @Test
    fun `if on linux notify is sent through LinuxGtkNotify`() {
        val title = "::Title::"
        val message = "::Message::"
        val platformIdentifier =
            mockk<PlatformIdentifier>(relaxed = true)
        val linuxGtkNotify = mockk<LinuxGtkNotify>(relaxed = true)
        val desktopTrayNotifier =
            mockk<DesktopTrayNotifier>(relaxed = true)
        every { platformIdentifier.isLinux() } returns true

        DesktopNotifier(
            platformIdentifier,
            linuxGtkNotify,
            desktopTrayNotifier
        ).send(title, message)

        verify(exactly = 1) {
            linuxGtkNotify.send(title, message)
        }
        verify(exactly = 0) {
            desktopTrayNotifier.sendNotification(title, message)
        }
    }

    @Test
    fun `if not on linux notify is sent through DesktopTrayNotifier`() {
        val title = "::Title::"
        val message = "::Message::"
        val platformIdentifier =
            mockk<PlatformIdentifier>(relaxed = true)
        val linuxGtkNotify = mockk<LinuxGtkNotify>(relaxed = true)
        val desktopTrayNotifier =
            mockk<DesktopTrayNotifier>(relaxed = true)
        every { platformIdentifier.isLinux() } returns false

        DesktopNotifier(
            platformIdentifier,
            linuxGtkNotify,
            desktopTrayNotifier
        ).send(title, message)

        verify(exactly = 0) {
            linuxGtkNotify.send(title, message)
        }
        verify(exactly = 1) {
            desktopTrayNotifier.sendNotification(title, message)
        }
    }

    @Test
    fun `should create a notification using notify-send`() {
        val title = "::Title::"
        val message = "::Message::"
        val notifySend = LinuxGtkNotify()
        val command = notifySend.makeCommand(title, message)
        command.shouldHaveSize(3)
        command.shouldContainInOrder(
            "/usr/bin/notify-send",
            title,
            message
        )

        // --to  test the send method, uncomment the following line
        // notifySend.send(title, message)
    }

    @Test
    fun `DesktopTrayNotifier adds icon to tray in setup`() {
        val tray = mockk<SystemTray>(relaxed = true)

        val notifier = DesktopTrayNotifier(tray)

        verify(exactly = 1) {  tray.add(notifier.trayIcon)}
    }


    @Test
    fun `DesktopTrayNotifier sends notification`() {
        val title = "::Title::"
        val message = "::Message::"

        val tray = mockk<SystemTray>(relaxed = true)
        val trayIcon = mockk<TrayIcon>(relaxed = true)

        val notifier = DesktopTrayNotifier(tray, trayIcon)
        notifier.sendNotification(title, message)

        verify(exactly = 1) {
            trayIcon.displayMessage(
                title,
                message,
                TrayIcon.MessageType.INFO
            )
        }
    }
}