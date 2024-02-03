package net.webspite.dataset.url;

import net.webspite.dataset.url.dsl.UrlDatasetDslLexer;
import net.webspite.dataset.url.dsl.UrlDatasetDslParser;
import net.webspite.dataset.url.model.UrlDataset;
import net.webspite.dataset.url.visitor.UrlDatasetVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

public class UrlDatasetParser {


    public UrlDataset parse(InputStream input) throws IOException {
        return parse(CharStreams.fromStream(input));
    }

    public UrlDataset parse(String input){
        return parse(CharStreams.fromString(input));
    }
    private UrlDataset parse(CharStream input){
        var lexer = new UrlDatasetDslLexer(input);
        var parser = new UrlDatasetDslParser(new CommonTokenStream(lexer));
        var visitor = new UrlDatasetVisitor();

        var cst = parser.parse();

        return visitor.visitParse(cst);
    }
}
