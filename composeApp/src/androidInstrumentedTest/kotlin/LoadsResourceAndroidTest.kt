import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import resources.ResourceLoader
import kotlin.test.Test

class LoadsResourceAndroidTest {

    @Test
    fun loadsAFileFromResources() {
        ResourceLoader().readTextFromFile("/test.txt")
            .shouldNotBeNull()
            .shouldBe("boo!")
    }
}