package notifications

interface PlatformSpecificNotification {
    fun send(title: String, message: String)
}
