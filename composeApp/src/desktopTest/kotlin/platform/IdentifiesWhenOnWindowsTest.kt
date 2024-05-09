package platform

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.Test

class IdentifiesWhenOnWindowsTest {

    @Test
    fun `should return true when on Windows`() {
        val sut = PlatformIdentifier("Windows")
        sut.isWindows()
            .shouldBeTrue()
        sut.isLinux()
            .shouldBeFalse()
        // -- this should pass on a windows system
        //PlatformIdentifier().isWindows().shouldBeTrue()
    }
}