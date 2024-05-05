package notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat

class AndroidNotificationSystem(val context: Context) {

    fun register(channel: NotificationChannel) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun send(notification: Notification) {
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
}
