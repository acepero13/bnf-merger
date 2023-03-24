package com.acepero13.research.bnfreplacer.model;

import java.util.Objects;

public record UniqueExpression(String symbol, String originalLine) implements Expression {
    @Override
    public String expression() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniqueExpression that = (UniqueExpression) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result ;
        return result;
    }
}
