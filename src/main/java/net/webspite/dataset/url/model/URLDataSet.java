package net.webspite.dataset.url.model;

import net.webspite.dataset.url.model.expr.Expr;

import java.util.List;

public record URLDataSet(Expr filter, List<Sort> sorts, Page page) {
    public URLDataSet() {
        this(null, null, null);
    }
}
