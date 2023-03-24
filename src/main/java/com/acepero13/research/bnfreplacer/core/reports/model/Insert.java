package com.acepero13.research.bnfreplacer.core.reports.model;

record Insert(String after, Operation operation) implements Difference {
    @Override
    public String report() {
        return "### Insertion ### \nAdded a new line at: " + " " + after;
    }
}
