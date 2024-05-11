package notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class AndroidNotificationChannelBuilder(
    val context: Context,
    val channelId: String = "",
    val channelDescription: String = "",
    val priority: Int = NotificationManager.IMPORTANCE_DEFAULT
) {
    fun withPriority(priority: Int): AndroidNotificationChannelBuilder {
        return AndroidNotificationChannelBuilder(
            context = this.context,
            channelId = this.channelId,
            channelDescription = this.channelDescription,
            priority = priority
        )
    }

    fun build(): NotificationChannel {
        return NotificationChannel(channelId, channelId, priority)
            .apply { description = channelDescription }
    }

    fun withChannelDescription(channelDescription: String): AndroidNotificationChannelBuilder {
        return AndroidNotificationChannelBuilder(
            context = this.context,
            channelId = this.channelId,
            channelDescription = channelDescription,
            priority = this.priority
        )
    }

    fun withChannelId(channelId: String): AndroidNotificationChannelBuilder {
        return AndroidNotificationChannelBuilder(
            context = this.context,
            channelId = channelId,
            channelDescription = this.channelDescription,
            priority = this.priority
        )
    }

    companion object {
        fun createNotificationChannel(
            context: Context,
            channelId: String
        ): NotificationChannel {
            return AndroidNotificationChannelBuilder(context)
                .withChannelId(channelId)
                .build()
        }
    }
}
