package net.webspite.dataset.url.visitor;

import net.webspite.dataset.url.dsl.UrlDatasetDslParser;
import net.webspite.dataset.url.dsl.UrlDatasetDslBaseVisitor;
import net.webspite.dataset.url.model.Page;
import net.webspite.dataset.url.model.Sort;
import net.webspite.dataset.url.model.SortOption;
import net.webspite.dataset.url.model.UrlDataset;
import net.webspite.dataset.url.model.expr.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class UrlDatasetVisitor extends UrlDatasetDslBaseVisitor<Object> {

    @Override
    public UrlDataset visitParse(UrlDatasetDslParser.ParseContext ctx) {
        return visitRoot(ctx.root());
    }

    @Override
    public UrlDataset visitRoot(UrlDatasetDslParser.RootContext ctx) {
        UrlDataset result = new UrlDataset();
        if(ctx.root() != null){
            result = visitRoot(ctx.root());
        }

        if(ctx.sort() != null && result.sorts().isEmpty()){
            result = new UrlDataset(result.filter(), visitSort(ctx.sort()), result.page());
        }else if(ctx.filter() != null  && result.filter() == null){
            result = new UrlDataset(visitFilter(ctx.filter()), result.sorts(), result.page());
        }else if(ctx.page() != null && result.page() == null){
            result = new UrlDataset(result.filter(), result.sorts(), visitPage(ctx.page()));
        }

        return result;
    }

    @Override
    public Expr visitFilter(UrlDatasetDslParser.FilterContext ctx) {
        if(ctx.or_expr() == null) return null;
        return visitOr_expr(ctx.or_expr());
    }
    @Override
    public Expr visitOr_expr(UrlDatasetDslParser.Or_exprContext ctx) {
        if(ctx.or_expr_ne() == null) return null;
        return visitOr_expr_ne(ctx.or_expr_ne());
    }
    @Override
    public Expr visitOr_expr_ne(UrlDatasetDslParser.Or_expr_neContext ctx) {
        if(ctx.or_expr_ne() == null) return visitAnd_expr(ctx.and_expr());
        return new OrExpr(visitAnd_expr(ctx.and_expr()), visitOr_expr_ne(ctx.or_expr_ne()));
    }

    @Override
    public Expr visitAnd_expr(UrlDatasetDslParser.And_exprContext ctx) {
        if(ctx.and_expr() == null) return visitFilter_term(ctx.filter_term());
        return new AndExpr(visitFilter_term(ctx.filter_term()), visitAnd_expr(ctx.and_expr()));
    }

    @Override
    public Expr visitFilter_term(UrlDatasetDslParser.Filter_termContext ctx) {
        return (Expr)visit(ctx.getChild(0));
    }

    @Override
    public Expr visitFilter_term_eq(UrlDatasetDslParser.Filter_term_eqContext ctx) {
        return new EqExpr(ctx.ID().getText(), ctx.literal().getText());
    }

    @Override
    public Expr visitFilter_term_starts_with(UrlDatasetDslParser.Filter_term_starts_withContext ctx) {
        return new StartsWithExpr(ctx.ID().getText(), ctx.literal().getText());
    }

    @Override
    public Expr visitFilter_term_ends_with(UrlDatasetDslParser.Filter_term_ends_withContext ctx) {
        return new EndsWithExpr(ctx.ID().getText(), ctx.literal().getText());
    }

    @Override
    public Expr visitFilter_term_contains(UrlDatasetDslParser.Filter_term_containsContext ctx) {
        return new ContainsExpr(ctx.ID().getText(), ctx.literal().getText());
    }

    @Override
    public Expr visitFilter_term_not(UrlDatasetDslParser.Filter_term_notContext ctx) {
        return new NotExpr(visitOr_expr_ne(ctx.or_expr_ne()));
    }

    @Override
    public Expr visitFilter_term_nested(UrlDatasetDslParser.Filter_term_nestedContext ctx) {
        return visitOr_expr_ne(ctx.or_expr_ne());
    }

    @Override
    public List<Sort> visitSort(UrlDatasetDslParser.SortContext ctx) {
        return visitSort_exprs(ctx.sort_exprs());
    }

    @Override
    public List<Sort> visitSort_exprs(UrlDatasetDslParser.Sort_exprsContext ctx) {
        List<Sort> result = new ArrayList<>();
        result.add(visitSort_expr(ctx.sort_expr()));
        if(ctx.sort_exprs() != null){
            result.addAll(visitSort_exprs(ctx.sort_exprs()));
        }
        return result;
    }

    @Override
    public Sort visitSort_expr(UrlDatasetDslParser.Sort_exprContext ctx) {
        return new Sort(ctx.ID().getText(), visitSort_options(ctx.sort_options()));
    }

    @Override
    public EnumSet<SortOption> visitSort_options(UrlDatasetDslParser.Sort_optionsContext ctx) {
        EnumSet<SortOption> result = EnumSet.noneOf(SortOption.class);
        if(ctx.sort_option() != null) {
            result.add(visitSort_option(ctx.sort_option()));
            result.addAll(visitSort_options(ctx.sort_options()));
        }
        return result;
    }

    @Override
    public SortOption visitSort_option(UrlDatasetDslParser.Sort_optionContext ctx) {
        return (SortOption)visit(ctx.getChild(0));
    }

    @Override
    public SortOption visitSort_option_asc(UrlDatasetDslParser.Sort_option_ascContext ctx) {
        return SortOption.Ascending;
    }

    @Override
    public SortOption visitSort_option_desc(UrlDatasetDslParser.Sort_option_descContext ctx) {
        return SortOption.Descending;
    }

    @Override
    public SortOption visitSort_option_nulls_first(UrlDatasetDslParser.Sort_option_nulls_firstContext ctx) {
        return SortOption.NullsFirst;
    }

    @Override
    public SortOption visitSort_option_nulls_last(UrlDatasetDslParser.Sort_option_nulls_lastContext ctx) {
        return SortOption.NullsLast;
    }

    @Override
    public SortOption visitSort_option_alphanumeric(UrlDatasetDslParser.Sort_option_alphanumericContext ctx) {
        return SortOption.Alphanumeric;
    }

    @Override
    public Object visitLiteral(UrlDatasetDslParser.LiteralContext ctx) {
        return null;
    }

    @Override
    public Page visitPage(UrlDatasetDslParser.PageContext ctx) {
        return new Page(Integer.parseInt(ctx.number_node.getText()), Integer.parseInt(ctx.size_node.getText()));
    }

    @Override
    public Object visitNumber(UrlDatasetDslParser.NumberContext ctx) {
        return null;
    }
}
