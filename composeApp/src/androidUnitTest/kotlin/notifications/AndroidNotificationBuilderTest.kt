package notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.shouldBe
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AndroidNotificationBuilderTest {

    @Test
    fun `AndroidNotificationBuilder makes a builder with the correct channel id`() {
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val context: Context = ApplicationProvider.getApplicationContext()

        AndroidNotificationBuilder(context, channelId, channelDescription)
            .build()
            .channelId
            .shouldBe(channelId)
    }

    @Test
    fun `AndroidNotificationBuilder makes a notificationChannel with correct priority`() {
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val context: Context = ApplicationProvider.getApplicationContext()

        val channel = AndroidNotificationBuilder(context, channelId, channelDescription)
            .withPriority(priority)
            .buildChannel()
        channel
            .id
            .shouldBe(channelId)
        channel
            .importance
            .shouldBe(priority)
        channel.description
            .shouldBe(channelDescription)
    }

    @Test
    fun `AndroidNotificationBuilder makes a notification with properties`() {
        val titleText = "::SOME TITLE::"
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val textContent = "::SOME TEXT::"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val context: Context = ApplicationProvider.getApplicationContext()

        val notification = AndroidNotificationBuilder(context, channelId, channelDescription)
            .withTitle(titleText)
            .withText(textContent)
            .withPriority(priority)
            .build()

        verifyTitle(notification, titleText)

        verifyText(notification, textContent)
    }

    private fun verifyText(notification: Notification, textContent: String) {
        notification
            .extras
            .getString(Notification.EXTRA_TEXT)
            .shouldBe(textContent)
    }

    private fun verifyTitle(notification: Notification, titleText: String) {
        notification
            .extras
            .getString(Notification.EXTRA_TITLE)
            .shouldBe(titleText)
    }


}