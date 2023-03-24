package com.acepero13.research.bnfreplacer.core.reports.model;

import java.util.List;
import java.util.function.Consumer;

public record Report(List<Difference> differences) {
    public int totalChanges() {
        return differences.size();
    }

    public void forEach(Consumer<String> consumer) {
        differences.stream()
                .map(Difference::report)
                .forEach(consumer);
        ;
    }

    public int totalInsertions() {
        return (int) differences.stream()
                .filter(d -> d.operation() == Difference.Operation.INSERTION)
                .count();
    }

    public int totalUpdates() {
        return (int) differences.stream()
                .filter(d -> d.operation() == Difference.Operation.UPDATE)
                .count();
    }
}
