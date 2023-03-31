package com.acepero13.research.bnfreplacer.model;

record Pragma(String expression, String originalLine) implements UniqueExpression {

    public static final String PRAGMA = "pragma";

    @Override
    public String symbol() {
        return PRAGMA;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pragma)) {
            return false;
        }
        return isEqual(o);
    }
}
