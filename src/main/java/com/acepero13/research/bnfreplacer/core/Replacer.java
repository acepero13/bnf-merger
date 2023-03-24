package com.acepero13.research.bnfreplacer.core;

import com.acepero13.research.bnfreplacer.model.Expression;
import com.acepero13.research.bnfreplacer.utils.Reader;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public final class Replacer {
    private final Bnf destination;
    private final Bnf source;

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
            if (destination.contains(sourceExpression)) {
                destination.update(sourceExpression);
            } else {
                destination.add(sourceExpression);
            }
        }
        return destination.asStrings();
    }

}
