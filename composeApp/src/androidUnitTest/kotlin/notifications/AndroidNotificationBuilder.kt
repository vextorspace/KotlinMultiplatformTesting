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
    private var priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    private var smallIcon: Int? = null
) {
    fun withTitle(title: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            this.context,
            this.channelId,
            this.channelDescription,
            title,
            this.text,
            this.priority,
            this.smallIcon
        )
    }

    fun withText(text: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            this.context,
            this.channelId,
            this.channelDescription,
            this.title,
            text,
            this.priority,
            this.smallIcon
        )
    }

    fun withPriority(priority: Int): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            this.context,
            this.channelId,
            this.channelDescription,
            this.title,
            this.text,
            priority,
            this.smallIcon
        )
    }

    fun withSmallIcon(smallIcon: Int?): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            this.context,
            this.channelId,
            this.channelDescription,
            this.title,
            this.text,
            this.priority,
            smallIcon
        )
    }

    fun build(): Notification {
        return builder().build()
    }

    private fun builder(): NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(text)

        addIconTo(builder)

        return builder
    }

    private fun addIconTo(builder: NotificationCompat.Builder) {
        smallIcon?.let {
            builder.setSmallIcon(it)
        }
    }

    fun buildChannel(): NotificationChannel {
        return NotificationChannel(channelId, channelId, priority)
            .apply { description = channelDescription }
    }
}
