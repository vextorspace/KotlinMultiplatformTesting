import io.kotest.matchers.booleans.shouldNotBeFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class KotlinAndroidUnitTest {

    @Test
    fun `test a kotlin assertion in android unit test`() {
        assertTrue(true)
    }

    @Test
    fun testKotestAssertion() {
        true.shouldNotBeFalse()
    }
}