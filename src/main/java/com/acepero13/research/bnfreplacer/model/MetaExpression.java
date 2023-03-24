package com.acepero13.research.bnfreplacer.model;

public record MetaExpression( String originalLine) implements Expression {


    @Override
    public String symbol() {
        return originalLine;
    }

    @Override
    public String expression() {
        return originalLine;
    }
}
