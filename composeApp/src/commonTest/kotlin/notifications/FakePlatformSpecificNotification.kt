package notifications

class FakePlatformSpecificNotification : PlatformSpecificNotification {
    val sentNotifications = mutableListOf<Pair<String, String>>()

    override fun send(title: String, message: String) {
        sentNotifications.add(title to message)
    }
}
