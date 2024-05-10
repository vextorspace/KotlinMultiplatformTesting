package notifications

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import kotlin.test.Test

class NotificationSendsTest {

    @Test
    fun `Notification System delegates to platform implementation`() {
        val notificationImplementation = FakePlatformSpecificNotification()

        val notificationSystem = NotificationSystem(notificationImplementation)

        val title = "::TITLE::"
        val message = "::NOTIFICATION MESSAGE::"
        notificationSystem.send(title, message)

        notificationImplementation.sentNotifications
            .shouldHaveSize(1)
            .shouldContain(title to message)
    }
}