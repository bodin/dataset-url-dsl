package net.webspite.dataset.url.model;

import net.webspite.dataset.url.model.expr.Expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record UrlDataset(Expr filter, List<Sort> sorts, Page page) {
    public UrlDataset() {
        this(null, Collections.emptyList(), null);
    }
}
