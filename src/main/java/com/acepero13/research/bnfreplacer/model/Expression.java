package com.acepero13.research.bnfreplacer.model;

import com.acepero13.research.bnfreplacer.parser.ExpressionTable;

public interface Expression {

    static Expression of(String testLine) {
        return ExpressionTable.of(testLine).orElse(Expression.metaExpression(testLine));
    }

    String originalLine();

    String symbol();

    String expression();

    default boolean representsSameExpressionAs(Expression expr) {
        return this.getClass().isInstance(expr) && symbol().equals(expr.symbol());
    }

    static Expression metaExpression(String originalLine){// TODO: Find better name
        return new MetaExpression(originalLine);
    }
}
