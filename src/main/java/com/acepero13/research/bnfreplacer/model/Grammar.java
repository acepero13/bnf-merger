package com.acepero13.research.bnfreplacer.model;

record Grammar(String expression, String originalLine) implements UniqueExpression {

    public static final String GRAMMAR = "grammar";

    @Override
    public String symbol() {
        return GRAMMAR;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grammar)) {
            return false;
        }
        return isEqual(o);
    }
}
