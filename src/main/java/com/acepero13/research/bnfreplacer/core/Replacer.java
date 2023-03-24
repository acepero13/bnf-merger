package com.acepero13.research.bnfreplacer.core;

import com.acepero13.research.bnfreplacer.core.reports.model.Difference;
import com.acepero13.research.bnfreplacer.core.reports.model.Report;
import com.acepero13.research.bnfreplacer.model.Expression;
import com.acepero13.research.bnfreplacer.utils.Reader;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Replacer {
    private final Bnf destination;
    private final Bnf source;
    private final List<Difference> differences = new ArrayList<>();

    private Replacer(Bnf source, Bnf destination) {
        this.source = source;
        this.destination = destination;
    }

    public static Replacer of(List<String> source, List<String> destination) {
        return new Replacer(Bnf.ofStrings(source), Bnf.ofStrings(destination));
    }

    public static Replacer of(InputStream source, InputStream destination) {
        return Replacer.of(Reader.from(source), Reader.from(destination));
    }

    public static Replacer of(Path source, Path destination) {
        return Replacer.of(Reader.from(source), Reader.from(destination));
    }

    // TODO: Ignore delete for now
    public List<String> replaceAll() {
        destination.clear();
        for (Expression sourceExpression : source) {
            process(sourceExpression);
        }
        return destination.asStrings();
    }

    private void process(Expression sourceExpression) {
        if (destination.contains(sourceExpression)) {
            Difference update = destination.update(sourceExpression);
            differences.add(update);
        } else {
            destination.add(sourceExpression);
            differences.add(Difference.insert(sourceExpression));
        }
    }


    public Report report() {
        List<Difference> report = differences.stream()
                .filter(Difference::wasChanged)
                .collect(Collectors.toList());
        return new Report(report);
    }
}
