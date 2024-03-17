import kotlin.test.Test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.nulls.shouldNotBeNull
import resources.ResourceLoader

class LoadsResourceAndroidUnitTest {

    @Test
    fun `loads a file from the resources dir`() {
        val text = ResourceLoader().readTextFromFile("/test.txt")
            .shouldNotBeNull()
            .shouldBe("boo!")
    }
}