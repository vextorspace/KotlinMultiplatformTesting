package notifications

class LinuxGtkNotify(val title: String, val message: String) {
    fun makeCommand(): List<String> {
        return listOf(
            "/usr/bin/notify-send",
            title,
            message
        )
    }

    fun send() {
        try {
            val process = ProcessBuilder(makeCommand())
                .start()
            process.waitFor()
        } catch (e: Exception) {

        }
    }

}
