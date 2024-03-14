import io.kotest.matchers.booleans.shouldNotBeFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class KotlinAndroidInstrumentedTest {

    @Test
    fun testKotlinTestAssertion() {
        assertTrue(true)
    }

    @Test
    fun testKotestAssertion() {
        true.shouldNotBeFalse()
    }
}