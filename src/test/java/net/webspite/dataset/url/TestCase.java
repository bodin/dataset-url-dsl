package net.webspite.dataset.url;

import net.webspite.dataset.url.dsl.DatasetUrlDslLexer;
import net.webspite.dataset.url.dsl.DatasetUrlDslParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import net.webspite.dataset.url.visitor.Visitor;

public class TestCase {

    @Test
    public void test() throws Exception {
        var input = CharStreams.fromString("filter(eq(a, 0.0) and not(starts_with(b, c)))" );
        var lexer = new DatasetUrlDslLexer(input);
        var parser = new DatasetUrlDslParser(new CommonTokenStream(lexer));

        var cst = parser.parse();
        var ast = new Visitor().visit(cst);
        System.out.println(ast);
    }
}
