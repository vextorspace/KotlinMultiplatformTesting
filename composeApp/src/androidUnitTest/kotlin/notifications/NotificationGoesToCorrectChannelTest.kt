package notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
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
        val shadowNotificationManager = shadowNotificationManager(
            ApplicationProvider.getApplicationContext()
        )

        shadowNotificationManager.allNotifications
            .shouldBeEmpty()
    }

    @Test
    fun `add channel registers them`() {
        val channelId = "::THE CHANNEL ID::"
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val channel =
            AndroidNotificationChannelBuilder.createNotificationChannel(
                context,
                channelId
            )

        AndroidNotificationSystem(
            context
        ).register(channel)

        shadowNotificationManager(context)
            .notificationChannels
            .shouldContain(channel)
    }

    @Test
    fun `notification goes to correct channel`() {
        val channelId = "::THE CHANNEL ID::"
        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val notificationSystem =
            createAndroidNotificationSystemWithChannel(
                context,
                channelId
            )

        val notification = makeNotification(context, channelId)

        notificationSystem.send(notification)

        verifyNotificationAndChannel(context, channelId, notification)
    }


    private fun createAndroidNotificationSystemWithChannel(
        context: Context,
        channelId: String
    ): AndroidNotificationSystem {
        val notificationSystem = AndroidNotificationSystem(context)
        val channel =
            AndroidNotificationChannelBuilder.createNotificationChannel(
                context,
                channelId
            )

        notificationSystem.register(channel)
        return notificationSystem
    }

    private fun makeNotification(
        context: Context,
        channelId: String
    ) = AndroidNotificationBuilder(context)
        .withChannelId(channelId)
        .build()

    companion object {
        fun verifyNotificationAndChannel(
            context: Context,
            channelId: String,
            notification: Notification
        ) {
            val notificationReceived = shadowNotificationManager(context)
                .allNotifications
                .shouldHaveSize(1)
                .first()
            notificationReceived
                .channelId
                .shouldBe(channelId)
            notificationReceived
                .shouldBeEqual(notification)
        }

        fun shadowNotificationManager(context: Context):
                ShadowNotificationManager {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
            val shadowNotificationManager =
                Shadows.shadowOf(notificationManager)
            return shadowNotificationManager
        }
    }
}