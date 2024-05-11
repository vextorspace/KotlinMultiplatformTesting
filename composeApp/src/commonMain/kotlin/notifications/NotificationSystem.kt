package notifications

class NotificationSystem(private val notificationImplementation: PlatformSpecificNotification) {
    fun send(title: String, message: String) {
        notificationImplementation.send(title, message)
    }
}
