package net.webspite.dataset.url

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import net.webspite.dataset.url.model.SortOption

class SortTest : StringSpec({
    "parse sort(person.lastName)" {
        val input = "sort(person.lastName)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 0
    }

    "parse sort(person.lastName asc)" {
        val input = "sort(person.lastName asc)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 1
        result.sorts[0].options shouldContain SortOption.Ascending
    }

    "parse sort(person.lastName desc)" {
        val input = "sort(person.lastName desc)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 1
        result.sorts[0].options shouldContain SortOption.Descending
    }

    "parse sort(person.lastName nulls first)" {
        val input = "sort(person.lastName nulls first)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 1
        result.sorts[0].options shouldContain SortOption.NullsFirst
    }

    "parse sort(person.lastName nulls last)" {
        val input = "sort(person.lastName nulls last)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 1
        result.sorts[0].options shouldContain SortOption.NullsLast
    }

    "parse sort(person.lastName alphanumeric)" {
        val input = "sort(person.lastName alphanumeric)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 1
        result.sorts[0].options shouldContain SortOption.Alphanumeric
    }

    "parse sort(person.lastName asc nulls last alphanumeric)" {
        val input = "sort(person.lastName asc nulls last alphanumeric)"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.filter shouldBe null
        result.page shouldBe null

        result.sorts shouldHaveSize 1
        result.sorts[0].id shouldBe "person.lastName"
        result.sorts[0].options shouldHaveSize 3
        result.sorts[0].options shouldContain SortOption.Alphanumeric
        result.sorts[0].options shouldContain SortOption.NullsLast
        result.sorts[0].options shouldContain SortOption.Ascending
    }
})