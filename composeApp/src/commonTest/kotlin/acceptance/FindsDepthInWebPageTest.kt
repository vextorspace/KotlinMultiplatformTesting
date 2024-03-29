package acceptance

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import web.DamReading
import web.DamTime
import web.FindsDepthInDamPage
import web.WebPage
import kotlin.test.Test

class FindsDepthInWebPageTest {

    @Test
    fun `given a dam web page finds the most recent depth of the dam`() {
        val reading: DamReading? = FindsDepthInDamPage().findDepth(WebPage.fromResource("/damDepth.html"))

        reading.shouldNotBeNull()
            .shouldBe(DamReading(508.0, DamTime("3/2/2024 1:45 PM")))

    }
}