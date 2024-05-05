package notifications

import android.app.Notification
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
        val context: Context = ApplicationProvider.getApplicationContext()

        val notification = AndroidNotificationBuilder(context, channelId)
            .build()
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            notification.channelId
                .shouldBe(channelId)
        } else {
            notification.extras
                .getString(Notification.EXTRA_CHANNEL_ID)
                .shouldBe(channelId)
        }
    }

    @Test
    fun `AndroidNotificationBuilder makes a notification with properties`() {
        val titleText = "::SOME TITLE::"
        val channelId = "::THE CHANNEL ID::"
        val context: Context = ApplicationProvider.getApplicationContext()

        val notification = AndroidNotificationBuilder(context, channelId)
            .withTitle(titleText)
            .build()

        notification
             .extras
             .getString(Notification.EXTRA_TITLE)
             .shouldBe(titleText)
    }
}