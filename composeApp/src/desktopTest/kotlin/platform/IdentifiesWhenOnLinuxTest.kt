package platform

import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Test

class IdentifiesWhenOnLinuxTest {

    @Test
    fun `should identify when on Linux`() {
        println(PlatformIdentifier.osName())
        PlatformIdentifier("Linux").isLinux()
            .shouldBeTrue()
    }
}