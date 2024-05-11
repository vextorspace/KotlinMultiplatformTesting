package com.ronnev.testing

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import notifications.AndroidNotificationChannelBuilder
import notifications.AndroidNotificationSystem
import notifications.NotificationSystem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationSystem = setupNotificationSystem()

        setContent {
            App(notificationSystem)
        }
    }

    private fun setupNotificationSystem(): NotificationSystem {
        val channel =
            AndroidNotificationChannelBuilder.createNotificationChannel(
                this,
                "Kotlin Multiplatform Testing Channel"
            )
        val androidNotificationSystem = AndroidNotificationSystem(this)
        androidNotificationSystem.register(channel)

        val notificationSystem = NotificationSystem(
            androidNotificationSystem
        )
        return notificationSystem
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val notificationSystem = NotificationSystem(
        AndroidNotificationSystem(null)
    )

    App(notificationSystem)
}