package com.acepero13.research.bnfreplacer.model;

record Language(String expression, String originalLine) implements UniqueExpression {

    public static final String LANGUAGE = "language";

    @Override
    public String symbol() {
        return LANGUAGE;
    }

    @Override
    public String expression() {
        return expression;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Language)) {
            return false;
        }
        return isEqual(o);
    }
}
