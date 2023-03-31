package com.acepero13.research.bnfreplacer.model;

import com.acepero13.research.bnfreplacer.parser.ExpressionTable;

public sealed interface Expression permits Expression.UnrecognizedExpression, RuleSymbol, Skip, StartSymbol, UniqueExpression {

    static Expression of(String testLine) {
        return ExpressionTable.of(testLine).orElse(Expression.verbatimExpression(testLine));
    }

    static Expression verbatimExpression(String testLine) {
        return new UnrecognizedExpression(testLine);
    }

    static Expression grammar(String expression, String line) {
        return new Grammar(Expression.sanitize(expression), line);
    }

    static Expression language(String expression, String line) {
        return new Language(Expression.sanitize(expression), line);
    }

    static Expression pragma(String expression, String line) {
        return new Pragma(Expression.sanitize(expression), line);
    }

    static Expression rule(String symbol, String expression, String line) {
        return new RuleSymbol(Expression.sanitize(symbol), Expression.sanitize(expression), line);
    }

    static Expression start(String expression, String line) {
        return new StartSymbol(sanitize(expression), line);
    }

    private static String sanitize(String str) {
        return str.trim();
    }

    static Expression skip() {
        return new Skip();
    }

    String originalLine();

    String symbol();

    String expression();

    default boolean representsSameExpressionAs(Expression expr) {
        return this.getClass().isInstance(expr) && symbol().equals(expr.symbol());
    }



    default boolean contains(String expression) {
        return originalLine().contains(expression);
    }

    final class UnrecognizedExpression implements Expression {
        private final String testLine;

        public UnrecognizedExpression(String testLine) {
            this.testLine = testLine;
        }

        @Override
        public String originalLine() {
            return testLine;
        }

        @Override
        public String symbol() {
            return testLine;
        }

        @Override
        public String expression() {
            return testLine;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Expression)) {
                return false;
            }
            return originalLine().equals(((Expression) obj).originalLine());
        }

        @Override
        public int hashCode() {
            return originalLine().hashCode();
        }

        @Override
        public String toString() {
            return originalLine();
        }
    }
}
