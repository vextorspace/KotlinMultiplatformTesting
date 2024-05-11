import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import notifications.DesktopNotifier
import notifications.NotificationSystem

fun main() = application {
    val notificationSystem = NotificationSystem(DesktopNotifier())

    Window(onCloseRequest = ::exitApplication, title = "KotlinProject") {
        App(notificationSystem)
    }
}