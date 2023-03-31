package com.acepero13.research.bnfreplacer.core;

import com.acepero13.research.bnfreplacer.core.reports.model.Difference;
import com.acepero13.research.bnfreplacer.model.Expression;
import com.acepero13.research.bnfreplacer.plugins.Preprocessing;
import com.acepero13.research.bnfreplacer.plugins.SkipDummySlots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

final class Bnf implements Iterable<Expression> {
    private final List<Expression> expressions;
    private final MutableBnf mutableBnf;
    private static final List<Preprocessing> preprocessing = List.of(new SkipDummySlots());


    private Bnf(List<Expression> expressions) {
        this.expressions = Collections.unmodifiableList(expressions);
        this.mutableBnf = new MutableBnf(expressions);
    }

    public static Bnf ofExpressions(List<Expression> expressions) {
        return new Bnf(expressions);
    }

    public static Bnf ofStrings(List<String> strings) {
        List<Expression> expressions = strings.stream()
                                              .filter(s -> preprocessing.stream().allMatch(p -> p.isAllowed(s)))
                                              .map(Expression::of)
                                              .toList();
        return new Bnf(expressions);
    }


    @Override
    public Iterator<Expression> iterator() {
        return expressions.iterator();
    }

    public boolean contains(Expression sourceExpression) {
        return mutableBnf.contains(sourceExpression);

    }


    public void clear() {
        mutableBnf.clear();

    }

    public Difference update(Expression sourceExpression) {
        return mutableBnf.update(sourceExpression);
    }


    public void add(Expression sourceExpression) {
        mutableBnf.add(sourceExpression);
    }

    public List<String> asStrings() {
        return mutableBnf.asStrings();
    }

    private static class MutableBnf {

        private final List<Expression> originalExpressions;
        private final int firstStartPosition;
        private List<Expression> mutableExpressions;

        private MutableBnf(List<Expression> expressions) {
            this.originalExpressions = Collections.unmodifiableList(expressions);
            this.mutableExpressions = new ArrayList<>(expressions);
            this.firstStartPosition = IntStream.range(0, originalExpressions.size())
                    .filter(i -> isStartSymbol(originalExpressions.get(i)))
                    .findFirst()
                    .orElse(-1);
        }

        private boolean isStartSymbol(Expression expression) {
            return expression.contains("!start");
        }


        public boolean contains(Expression sourceExpression) {
            return mutableExpressions.stream().anyMatch(e -> e.representsSameExpressionAs(sourceExpression));
        }

        public Difference update(Expression sourceExpression) {
            return updateAtPosition(sourceExpression, indexOf(sourceExpression));
        }


        private Difference updateAtPosition(Expression sourceExpression, int index) {
            if (index == -1) {
                return Difference.noOp();
            }
            Expression original = mutableExpressions.get(index);
            if (!original.equals(sourceExpression)) {
                mutableExpressions.set(index, sourceExpression);
                return Difference.update(original, sourceExpression);
            }
            return Difference.noOp();
        }

        private int indexOf(Expression sourceExpression) {
            return IntStream.range(0, mutableExpressions.size())
                            .filter(i -> mutableExpressions.get(i).representsSameExpressionAs(sourceExpression))
                            .findFirst()
                            .orElse(-1);
        }

        public void add(Expression sourceExpression) {
            if(isStartSymbol(sourceExpression) && firstStartPosition > 0) {
                mutableExpressions.add(firstStartPosition, sourceExpression);
            } else {
                mutableExpressions.add(sourceExpression);
            }


        }



        public List<String> asStrings() {
            return mutableExpressions.stream().map(Expression::originalLine).toList();
        }

        public void clear() {
            this.mutableExpressions.clear();
            this.mutableExpressions = new ArrayList<>(originalExpressions);
        }
    }
}
