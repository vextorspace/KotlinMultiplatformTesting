package notifications

import android.app.Notification
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import notifications.NotificationGoesToCorrectChannelTest.Companion.shadowNotificationManager
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class NotificationDelegatesToAndroidTest {

    @Test
    fun delegatesToAndroid() {
        val channelId = "::THE CHANNEL ID::"
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val notificationSystem =
            setupNotificationSystem(context, channelId)

        val title = "::TITLE::"
        val message = "::NOTIFICATION MESSAGE::"

        notificationSystem.send(title, message)

        verifyNotification(context, channelId, title, message)
    }

    private fun setupNotificationSystem(
        context: Context,
        channelId: String
    ): NotificationSystem {
        val channel =
            AndroidNotificationChannelBuilder.createNotificationChannel(
                context,
                channelId
            )

        val androidNotificationSystem = AndroidNotificationSystem(
            context
        )
        androidNotificationSystem.register(channel)

        return NotificationSystem(androidNotificationSystem)
    }

    private fun verifyNotification(
        context: Context,
        channelId: String,
        title: String,
        message: String
    ) {
        val notificationReceived = shadowNotificationManager(context)
            .allNotifications
            .shouldHaveSize(1)
            .first()
        notificationReceived
            .channelId
            .shouldBe(channelId)
        notificationReceived.shouldHaveTitle(title)
            .shouldHaveMessage(message)
    }
}

private fun Notification.shouldHaveMessage(message: String): Notification {
    this.extras.getString(Notification.EXTRA_TEXT)
        .shouldBe(message)
    return(this)
}

private fun Notification.shouldHaveTitle(title: String): Notification {
    this.extras.getCharSequence(Notification.EXTRA_TITLE)
        .toString()
        .shouldBe(title)
    return this
}
