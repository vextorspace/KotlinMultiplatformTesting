package notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

class AndroidNotificationSystem(val context: Context) {

    fun register(channel: NotificationChannel) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}