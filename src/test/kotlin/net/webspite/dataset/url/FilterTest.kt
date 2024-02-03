package net.webspite.dataset.url

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.webspite.dataset.url.model.expr.ContainsExpr
import net.webspite.dataset.url.model.expr.EndsWithExpr
import net.webspite.dataset.url.model.expr.EqExpr
import net.webspite.dataset.url.model.expr.NotExpr
import net.webspite.dataset.url.model.expr.StartsWithExpr

class FilterTest : StringSpec({
    "parse filter(eq(person.name, Dave))" {
        val input = "filter(eq(person.name, Dave))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe EqExpr("person.name", "Dave")
    }

    "parse filter((eq(person.name, Dave)))" {
        val input = "filter(eq(person.name, Dave))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe EqExpr("person.name", "Dave")
    }

    "parse filter(starts_with(person.name, Dave))" {
        val input = "filter(starts_with(person.name, Dave))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe StartsWithExpr("person.name", "Dave")
    }

    "parse filter(ends_with(person.name, Dave))" {
        val input = "filter(ends_with(person.name, Dave))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe EndsWithExpr("person.name", "Dave")
    }

    "parse filter(contains(person.name, Dave))" {
        val input = "filter(contains(person.name, Dave))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe ContainsExpr("person.name", "Dave")
    }

    "parse filter(not(eq(person.name, Dave)))" {
        val input = "filter(not(eq(person.name, Dave)))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe NotExpr(EqExpr("person.name", "Dave"))
    }
})