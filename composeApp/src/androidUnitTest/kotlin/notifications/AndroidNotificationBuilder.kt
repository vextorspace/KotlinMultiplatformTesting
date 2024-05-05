package notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat

class AndroidNotificationBuilder(
    private val context: Context,
    private val channelId: String,
    private var title: String = ""
) {
    fun withTitle(title: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(context, channelId, title)
    }

    fun build(): Notification {
        return builder().build()
    }

    private fun builder() : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
    }
}
