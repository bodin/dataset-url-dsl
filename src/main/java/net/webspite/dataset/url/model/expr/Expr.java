package net.webspite.dataset.url.model.expr;

public sealed interface Expr permits AndExpr, ContainsExpr, EndsWithExpr, EqExpr, NotExpr, OrExpr, StartsWithExpr{}
