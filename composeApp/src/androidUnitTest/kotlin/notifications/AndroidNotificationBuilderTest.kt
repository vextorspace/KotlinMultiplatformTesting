package notifications

import android.app.Notification
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.equality.shouldBeEqualUsingFields
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
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        AndroidNotificationBuilder(
            context
        )
            .withChannelId(channelId)
            .build()
            .channelId
            .shouldBe(channelId)
    }


    @Test
    fun `AndroidNotificationBuilder notification has all properties`() {
        val titleText = "::SOME TITLE::"
        val channelId = "::THE CHANNEL ID::"
        val textContent = "::SOME TEXT::"
        val smallIcon: Int = android.R.drawable.ic_dialog_info
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val notification = AndroidNotificationBuilder(
            context,
            channelId,
        )
            .withTitle(titleText)
            .withText(textContent)
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