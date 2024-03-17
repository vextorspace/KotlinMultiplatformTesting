package resources

actual class ResourceLoader {
    actual fun readTextFromFile(path: String): String? {
        return this.javaClass.getResource(path)?.readText()
    }
}