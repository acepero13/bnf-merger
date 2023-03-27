package com.acepero13.research.bnfreplacer.core.reports.model;

record Update(String before, String after) implements Difference {
    @Override
    public Operation operation() {
        return Operation.UPDATE;
    }

    @Override
    public String report() {
        return "### Update ### " + "\nBefore: " + before + "\nAfter : " + after;
    }
}
