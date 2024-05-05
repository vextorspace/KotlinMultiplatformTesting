package notifications

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat

class AndroidNotificationBuilder(
    private val context: Context,
    private val channelId: String,
    private var title: String = "",
    private var text: String = ""
) {
    fun withTitle(title: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(this.context, this.channelId, title, this.text)
    }

    fun withText(textContent: String): AndroidNotificationBuilder {
        return AndroidNotificationBuilder(this.context, this.channelId, this.title, text)
    }

    fun build(): Notification {
        return builder().build()
    }

    private fun builder() : NotificationCompat.Builder {
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(text)
    }
}
