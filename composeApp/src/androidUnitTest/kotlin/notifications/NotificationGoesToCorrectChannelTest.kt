package notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowNotificationManager

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class NotificationGoesToCorrectChannelTest {

    @Test
    fun `no notification nothing shows up`() {
        val shadowNotificationManager =
            shadowNotificationManager(ApplicationProvider.getApplicationContext<Context>())

        shadowNotificationManager.allNotifications
            .shouldBeEmpty()
    }

    @Test
    fun `add channel registers them`() {
        val channelId = "::THE CHANNEL ID::"
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val channel = createNotificationChannel(context, channelId)

        AndroidNotificationSystem(
            context
        ).register(channel)

        shadowNotificationManager(context)
            .notificationChannels
            .shouldContain( channel )
    }

    private fun shadowNotificationManager(context: Context): ShadowNotificationManager {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        val shadowNotificationManager = Shadows.shadowOf(notificationManager)
        return shadowNotificationManager
    }

    private fun createNotificationChannel(
        context: Context,
        channelId: String
    ): NotificationChannel {
        val channelDescription = "::THE CHANNEL DESCRIPTION::"
        val channel = AndroidNotificationChannelBuilder(
            context
        )
            .withChannelId(channelId)
            .withChannelDescription(channelDescription)
            .build()
        return channel
    }
}