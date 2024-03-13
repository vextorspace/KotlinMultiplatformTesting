import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class KotlinAndroidInstrumentedTest {

    @Test
    fun testKotlinTestAssertion() {
        assertTrue(true)
        fail("whoops")
    }

}