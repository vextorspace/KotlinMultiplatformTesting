package notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat

class AndroidNotificationSystem(val context: Context?) :
    PlatformSpecificNotification {
    private lateinit var channel: NotificationChannel

    fun send(notification: Notification) {
        if(context == null) {
            return
        }

        val notificationManager =
            NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager
            .notify(notification.hashCode(), notification)
    }

    override fun send(title: String, message: String) {
        if(context == null) {
            return
        }

        val notification = AndroidNotificationBuilder(context)
            .withTitle(title)
            .withText(message)
            .withChannelId(channel.id)
            .withSmallIcon(com.ronnev.testing.R.drawable.icon)
            .build()

        send(notification)
    }

    fun register(channel: NotificationChannel) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE)
                    as? NotificationManager
        notificationManager?.createNotificationChannel(channel)

        this.channel = channel
    }
}
