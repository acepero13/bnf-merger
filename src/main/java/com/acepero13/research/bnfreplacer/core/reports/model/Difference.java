package com.acepero13.research.bnfreplacer.core.reports.model;

import com.acepero13.research.bnfreplacer.model.Expression;

public interface Difference {
    static Difference update(Expression original, Expression change) {
        return new Update(original.originalLine(), change.originalLine(), Operation.UPDATE);
    }

    static Difference insert(Expression change) {
        return new Insert(change.originalLine(), Operation.INSERTION);
    }

    Operation operation();

    String report();

    default boolean wasChanged() {
        return true;
    }

    static Difference noOp() {
        return new Difference() {
            @Override
            public Operation operation() {
                return Operation.NOP;
            }

            @Override
            public String report() {
                return "";
            }

            @Override
            public boolean wasChanged() {
                return false;
            }
        };
    }

    enum Operation {
        INSERTION, UPDATE, NOP;
    }
}
