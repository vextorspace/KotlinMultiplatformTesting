package notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat

class AndroidNotificationBuilder(
    private val context: Context,
    private val channelId: String = "",
    private var title: String = "",
    private var text: String = "",
    private var smallIcon: Int? = null
) {
    fun build(): Notification {
        return builder().build()
    }

    fun withTitle(title: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            context = this.context,
            channelId = this.channelId,
            title = title,
            text = this.text,
            smallIcon = this.smallIcon
        )
    }

    fun withText(text: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            context = this.context,
            channelId = this.channelId,
            title = this.title,
            text = text,
            smallIcon = this.smallIcon
        )
    }

    fun withChannelId(channelId: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            context = this.context,
            channelId = channelId,
            title = this.title,
            text = this.text,
            smallIcon = this.smallIcon
        )
    }

    fun withSmallIcon(smallIcon: Int?): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(
            context = this.context,
            channelId = this.channelId,
            title = this.title,
            text = this.text,
            smallIcon = smallIcon
        )
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
}
