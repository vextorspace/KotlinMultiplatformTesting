package notifications

class LinuxGtkNotify(val title: String, val message: String) {
    fun makeCommand(): List<String> {
        return listOf(
            "/usr/bin/notify-send",
            title,
            message
        )
    }

}
