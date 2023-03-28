package com.acepero13.research.bnfreplacer.model;

public sealed interface UniqueExpression extends Expression permits Grammar, Language, Pragma {
    @Override
    default boolean representsSameExpressionAs(Expression expr) {
        return symbol().equals(expr.symbol());
    }

    default boolean isEqual(Object object){
        return object instanceof UniqueExpression && ((UniqueExpression) object).symbol().equals(symbol());
    }

    boolean equals(Object o);
}
