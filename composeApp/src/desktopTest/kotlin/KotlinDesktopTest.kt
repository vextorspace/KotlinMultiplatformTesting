import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class KotlinDesktopTest {

    @Test
    fun `a kotlin-test assertion`() {
        assertTrue(true)
        fail("whoops")
    }
}