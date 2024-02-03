package net.webspite.dataset.url

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class FullTest : StringSpec({
    "parse filter(person.name=dave) sort(person.name) page(6 by 375)" {
        val input = "filter(eq(person.name,dave)) sort(person.name) page(6 by 375)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        //result.filter shouldBe null
        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.name"
        result.sorts[0].options shouldHaveSize 0

        result.page shouldNotBe null
        result.page.number shouldBe 6
        result.page.size shouldBe 375
    }
})