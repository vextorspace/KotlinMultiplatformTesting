package notifications

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import io.kotest.matchers.collections.shouldBeEmpty
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class NotificationGoesToCorrectChannelTest {

    @Test
    fun `no notification nothing shows up`() {
        val notificationManager =
            ApplicationProvider.getApplicationContext<Context>()
            .getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager

        val shadowNotificationManager =
            Shadows.shadowOf(notificationManager)

        shadowNotificationManager.allNotifications
            .shouldBeEmpty()
    }


}