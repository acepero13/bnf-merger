package com.acepero13.research.bnfreplacer.core;

import com.acepero13.research.bnfreplacer.core.reports.model.Difference;
import com.acepero13.research.bnfreplacer.model.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Bnf implements Iterable<Expression> {
    private final List<Expression> expressions;
    private List<Expression> modifiableExpressions;


    private Bnf(List<Expression> expressions) {
        this.expressions = Collections.unmodifiableList(expressions);
        this.modifiableExpressions = new ArrayList<>(expressions);
    }

    public static Bnf ofExpressions(List<Expression> expressions) {
        return new Bnf(expressions);
    }

    public static Bnf ofStrings(List<String> strings) {
        List<Expression> expressions = strings.stream().map(Expression::of).collect(Collectors.toList());
        return new Bnf(expressions);
    }


    @Override
    public Iterator<Expression> iterator() {
        return expressions.iterator();
    }

    public boolean contains(Expression sourceExpression) {
        return modifiableExpressions.stream().anyMatch(e -> e.representsSameExpressionAs(sourceExpression));
    }


    public void clear() {
        this.modifiableExpressions.clear();
        this.modifiableExpressions = new ArrayList<>(expressions);
    }

    public Difference update(Expression sourceExpression) {
        var index = indexOf(sourceExpression);
        if (index != -1) {
            Expression original = modifiableExpressions.get(index);
            if (!original.equals(sourceExpression)) {
                modifiableExpressions.set(index, sourceExpression);
                return Difference.update(original, sourceExpression);
            }
        }
        return Difference.noOp();
    }

    private int indexOf(Expression sourceExpression) {
        return IntStream.range(0, modifiableExpressions.size())
                .filter(i -> modifiableExpressions.get(i).representsSameExpressionAs(sourceExpression))
                .findFirst()
                .orElse(-1);
    }

    public void add(Expression sourceExpression) {
        modifiableExpressions.add(sourceExpression);
    }

    public List<String> asStrings() {
        return modifiableExpressions.stream().map(Expression::originalLine).collect(Collectors.toList());
    }
}
