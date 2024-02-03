package net.webspite.dataset.url

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PageTest : StringSpec({
    "parse page(2 by 13)" {
        val input = "page(2 by 13)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.sorts shouldHaveSize 0

        result.page shouldNotBe null
        result.page.number shouldBe 2
        result.page.size shouldBe 13
    }
})