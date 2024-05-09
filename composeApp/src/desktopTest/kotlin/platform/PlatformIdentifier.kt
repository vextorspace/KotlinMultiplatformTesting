package platform

class PlatformIdentifier(val osName: String = osName()) {
    fun isLinux(): Boolean {
        return osName.lowercase().contains("linux")
    }

    companion object {
        fun osName(): String {
            return System.getProperty("os.name")
        }
    }
}
