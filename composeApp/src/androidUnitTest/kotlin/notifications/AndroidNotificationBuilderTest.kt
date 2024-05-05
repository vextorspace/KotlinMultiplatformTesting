package notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AndroidNotificationBuilderTest {

    @Test
    fun `AndroidNotificationBuilder notification has channel id`() {
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        AndroidNotificationBuilder(
            context,
            channelId,
            channelDescription
        )
            .build()
            .channelId
            .shouldBe(channelId)
    }

    @Test
    fun `AndroidNotificationBuilder_notificationChannel has priority`() {
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val channel = AndroidNotificationBuilder(
            context,
            channelId,
            channelDescription
        )
            .withPriority(priority)
            .buildChannel()

        verifyChannelId(channel, channelId)
        verifyChannelPriority(channel, priority)
        verifyChannelDescription(channel, channelDescription)
    }

    private fun verifyChannelDescription(
        channel: NotificationChannel,
        channelDescription: String
    ) {
        channel.description
            .shouldBe(channelDescription)
    }

    private fun verifyChannelPriority(
        channel: NotificationChannel,
        priority: Int
    ) {
        channel
            .importance
            .shouldBeExactly(priority)
    }

    private fun verifyChannelId(
        channel: NotificationChannel,
        channelId: String
    ) {
        channel
            .id
            .shouldBe(channelId)
    }

    @Test
    fun `AndroidNotificationBuilder notification has all properties`() {
        val titleText = "::SOME TITLE::"
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val textContent = "::SOME TEXT::"
        val smallIcon: Int = android.R.drawable.ic_dialog_info
        val priority = NotificationManager.IMPORTANCE_HIGH
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val notification = AndroidNotificationBuilder(
            context,
            channelId,
            channelDescription
        )
            .withTitle(titleText)
            .withText(textContent)
            .withPriority(priority)
            .withSmallIcon(smallIcon)
            .build()

        verifyTitle(notification, titleText)

        verifyText(notification, textContent)

        verifySmallIconSetToSomething(notification, smallIcon, context)
    }

    private fun verifySmallIconSetToSomething(
        notification: Notification,
        smallIcon: Int,
        context: Context
    ) {
        val icon = Icon.createWithResource(context, smallIcon)

        notification
            .smallIcon
            .shouldBeEqualUsingFields(icon)
    }

    private fun verifyText(
        notification: Notification,
        textContent: String
    ) {
        notification
            .extras
            .getString(Notification.EXTRA_TEXT)
            .shouldBe(textContent)
    }

    private fun verifyTitle(
        notification: Notification,
        titleText: String
    ) {
        notification
            .extras
            .getString(Notification.EXTRA_TITLE)
            .shouldBe(titleText)
    }
}