package notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AndroidNotificationChannelBuilderTest {

    @Test
    fun `channel builder notificationChannel has priority`() {
        val channelId = "::THE CHANNEL ID::"
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val priority = NotificationManager.IMPORTANCE_HIGH
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val channel = AndroidNotificationChannelBuilder(
            context
        )
            .withChannelId(channelId)
            .withChannelDescription(channelDescription)
            .withPriority(priority)
            .build()

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
}