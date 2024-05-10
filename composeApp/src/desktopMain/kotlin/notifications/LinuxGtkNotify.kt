package notifications

class LinuxGtkNotify() {
    fun makeCommand(
        title: String,
        message: String
    ): List<String> {
        return listOf(
            "/usr/bin/notify-send",
            title,
            message
        )
    }

    fun send(
        title: String,
        message: String
    ) {
        val process = ProcessBuilder(makeCommand(title, message))
            .start()
        process.waitFor()
    }
}
