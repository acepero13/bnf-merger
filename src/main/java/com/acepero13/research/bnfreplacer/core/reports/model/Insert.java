package com.acepero13.research.bnfreplacer.core.reports.model;

record Insert(String after) implements Difference {
    @Override
    public Operation operation() {
        return Operation.INSERTION;
    }

    @Override
    public String report() {
        return "### Insertion ### \nAdded a new line at: " + " " + after;
    }
}
