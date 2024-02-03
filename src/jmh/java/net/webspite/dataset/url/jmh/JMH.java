package net.webspite.dataset.url.jmh;

import net.webspite.dataset.url.dsl.DatasetUrlDslLexer;
import net.webspite.dataset.url.dsl.DatasetUrlDslParser;
import net.webspite.dataset.url.visitor.UrlDatasetVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

public class JMH {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parse() {
        //var input = CharStreams.fromString("filter(eq(a, 0.0) and not(starts_with(b, c)))" );
        //var lexer = new DatasetUrlDslLexer(input);
        //var parser = new DatasetUrlDslParser(new CommonTokenStream(lexer));

        //var cst = parser.parse();
        //var ast = new UrlDatasetVisitor().visit(cst);
    }

}
