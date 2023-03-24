package com.acepero13.research.bnfreplacer.core.reports.model;

record Update(String before, String after, Operation operation) implements Difference {
    @Override
    public String report() {
        return "### Update ### " + "\nBefore: " + before + "\nAfter : " + after;
    }
}
