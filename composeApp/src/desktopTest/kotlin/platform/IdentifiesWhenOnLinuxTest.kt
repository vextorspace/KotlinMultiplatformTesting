package platform

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Test

class IdentifiesWhenOnLinuxTest {

    @Test
    fun `should identify when on Linux`() {
        println(PlatformIdentifier.osName())
        val sut = PlatformIdentifier("Linux")
        sut.isLinux()
            .shouldBeTrue()

        sut.isWindows()
            .shouldBeFalse()

        // -- this should pass on a linux system
        // PlatformIdentifier().isLinux().shouldBeTrue()
    }
}