import io.kotest.matchers.booleans.shouldNotBeFalse
import kotlin.test.Test
import kotlin.test.assertTrue

class KotlinTest {

    @Test
    fun `test a kotlin test assertion`() {
        assertTrue(true)
    }

    @Test
    fun testKotestAssertion() {
        true.shouldNotBeFalse()
    }
}