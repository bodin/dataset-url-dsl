package net.webspite.dataset.url

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import net.webspite.dataset.url.model.expr.AndExpr
import net.webspite.dataset.url.model.expr.ContainsExpr
import net.webspite.dataset.url.model.expr.EndsWithExpr
import net.webspite.dataset.url.model.expr.EqExpr
import net.webspite.dataset.url.model.expr.NotExpr
import net.webspite.dataset.url.model.expr.OrExpr
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

    "parse filter(eq(person.name, Dave) or eq(person.name, Betty))" {
        val input = "filter(eq(person.name, Dave) or eq(person.name, Betty))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe OrExpr(EqExpr("person.name", "Dave"), EqExpr("person.name", "Betty"))
    }
    "parse filter(eq(person.name, Dave) and eq(person.name, Betty))" {
        val input = "filter(eq(person.name, Dave) and eq(person.name, Betty))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe AndExpr(EqExpr("person.name", "Dave"), EqExpr("person.name", "Betty"))
    }

    "parse filter(eq(person.name, Dave) or eq(person.name, Betty) and eq(person.name, Diane))" {
        val input = "filter(eq(person.name, Dave) or eq(person.name, Betty) and eq(person.name, Diane))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe OrExpr(EqExpr("person.name", "Dave"), AndExpr(EqExpr("person.name", "Betty"), EqExpr("person.name", "Diane")))
    }

    "parse filter((eq(person.name, Dave) or eq(person.name, Betty)) and eq(person.name, Diane))" {
        val input = "filter((eq(person.name, Dave) or eq(person.name, Betty)) and eq(person.name, Diane))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe AndExpr(OrExpr(EqExpr("person.name", "Dave"), EqExpr("person.name", "Betty")), EqExpr("person.name", "Diane"))
    }

    "parse filter(eq(person.name, Dave) and eq(person.name, Betty) or eq(person.name, Diane))" {
        val input = "filter(eq(person.name, Dave) and eq(person.name, Betty) or eq(person.name, Diane))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe OrExpr(AndExpr(EqExpr("person.name", "Dave"), EqExpr("person.name", "Betty")), EqExpr("person.name", "Diane"))
    }

    "parse filter(eq(person.name, Dave) and (eq(person.name, Betty) or eq(person.name, Diane)))" {
        val input = "filter(eq(person.name, Dave) and (eq(person.name, Betty) or eq(person.name, Diane)))"
        val parser = UrlDatasetParser()
        val result = parser.parse(input)

        result.sorts shouldHaveSize 0
        result.page shouldBe null

        result.filter shouldNotBe null
        result.filter shouldBe AndExpr(EqExpr("person.name", "Dave"), OrExpr(EqExpr("person.name", "Betty"), EqExpr("person.name", "Diane")))
    }
})