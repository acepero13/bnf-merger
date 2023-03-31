package com.acepero13.research.bnfreplacer.parser;

import com.acepero13.research.bnfreplacer.model.Expression;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ExpressionTable {
    START_SYMBOL("^!start\\s*<([^>]+)>;$", (Matcher m, String line) -> Expression.start(m.group(1), line)),
    RULE_SYMBOL("^<([^>]+)>:\\s*(.+);$", (Matcher m, String line) -> Expression.rule(m.group(1), m.group(2), line)),
    GRAMMAR("^!grammar\\s*(\\w+);?$", (Matcher m, String line) -> Expression.grammar(m.group(1), line)),
    LANGUAGE("^!language\\s*\"([^\"]+)\";", (Matcher m, String line) -> Expression.language(m.group(1), line)),
    PRAGMA("BNF\\+EM\\s+(\\S+)", (Matcher m, String line) -> Expression.pragma(m.group(1), line));


    private final String regexPattern;
    private final BiFunction<Matcher, String, Expression> factory;

    ExpressionTable(String regexPattern, BiFunction<Matcher, String, Expression> factory) {
        this.regexPattern = regexPattern;
        this.factory = factory;
    }

    public static Optional<Expression> of(String line) {
        return Arrays.stream(values())
                .map(expressionRegex -> extractMatcherFrom(line, expressionRegex))
                .filter(ExpressionMatcher::isAMatch)
                .findFirst()
                .map(parser -> parser.parse(line));
    }

    private static ExpressionMatcher extractMatcherFrom(String line, ExpressionTable expressionRegex) {
        Pattern pattern = Pattern.compile(expressionRegex.regexPattern);
        return new ExpressionMatcher(pattern.matcher(line), expressionRegex);
    }

    private record ExpressionMatcher(Matcher matcher, ExpressionTable regex) {
        private boolean isAMatch() {
            return matcher.find();
        }

        public Expression parse(String line) {
            return regex.factory.apply(matcher, line);
        }
    }
}
