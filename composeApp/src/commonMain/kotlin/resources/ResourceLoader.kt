package resources

expect class ResourceLoader {
    fun readTextFromFile(path: String): String?
}
