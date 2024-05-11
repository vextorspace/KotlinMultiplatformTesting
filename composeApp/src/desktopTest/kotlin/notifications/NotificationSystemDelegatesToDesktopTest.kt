package notifications

import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test

class NotificationSystemDelegatesToDesktopTest {

    @Test
    fun `sends notification to desktop`() {
        val desktopNotifier = mockk<DesktopNotifier>(relaxed = true)
        val notificationSystem = NotificationSystem(desktopNotifier)

        val title = "::TITLE::"
        val message = "::NOTIFICATION MESSAGE::"

        notificationSystem.send(title, message)

        verify(exactly = 1) {
            desktopNotifier.send(title, message)
        }
    }
}