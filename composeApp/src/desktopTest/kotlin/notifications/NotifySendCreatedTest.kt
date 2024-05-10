package notifications

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.mockk
import io.mockk.verify
import java.awt.SystemTray
import java.awt.TrayIcon
import kotlin.test.Test

class NotifySendCreatedTest {

    @Test
    fun `should create a notification using notify-send`() {
        val title = "::Title::"
        val message = "::Message::"
        val notifySend = LinuxGtkNotify(title, message)
        val command: List<String> = notifySend.makeCommand()
        command.shouldHaveSize(3)
        command.shouldContainInOrder(
            "/usr/bin/notify-send",
            title,
            message
        )

        // --to actually test the send method, uncomment the following line
        // notifySend.send()
    }

    @Test
    fun `DesktopTrayNotifier adds icon to tray in setup`() {
        val tray = mockk<SystemTray>(relaxed = true)

        val notifier = DesktopTrayNotifier(tray)
        val trayIcon = notifier.trayIcon

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