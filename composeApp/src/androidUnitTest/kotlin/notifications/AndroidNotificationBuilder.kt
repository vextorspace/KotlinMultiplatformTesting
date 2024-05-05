package notifications

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat

class AndroidNotificationBuilder(
    private val context: Context,
    private val channelId: String,
    private val channelDescription: String,
    private var title: String = "",
    private var text: String = "",
    private var priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    fun withTitle(title: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(this.context, this.channelId, this.channelDescription, title, this.text, this.priority)
    }

    fun withText(text: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(this.context, this.channelId, this.channelDescription, this.title, text, this.priority)
    }

    fun withPriority(priority: Int): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(this.context, this.channelId, this.channelDescription, this.title, this.text, priority)
    }

    fun build(): Notification {
        return builder().build()
    }

    private fun builder() : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(text)
    }

    fun buildChannel(): NotificationChannel {
        return NotificationChannel(channelId, channelId, priority)
            .apply { description = channelDescription }
    }
}
