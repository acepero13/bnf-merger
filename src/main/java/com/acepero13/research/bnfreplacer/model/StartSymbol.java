package com.acepero13.research.bnfreplacer.model;

public record StartSymbol(String expression, String originalLine) implements Expression {

    private static final String SYMBOL = "!start";
    @Override
    public String symbol() {
        return SYMBOL + "_" + expression;
    }
}
