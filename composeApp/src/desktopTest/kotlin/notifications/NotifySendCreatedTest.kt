package notifications

import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.collections.shouldHaveSize
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
    }
}