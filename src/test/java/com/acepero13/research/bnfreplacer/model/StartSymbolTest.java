package com.acepero13.research.bnfreplacer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StartSymbolTest {

    @Test
    @DisplayName("Reads out start symbol: !start")
    void readSymbol() {
        var testLine = "!start <Cancel>;";
        Expression start = Expression.of(testLine);
        Expression expected = new StartSymbol("Cancel", testLine);
        assertThat(start, equalTo(expected));

    }

    @Test
    @DisplayName("Returns the bnf line")
    void toLine() {
        var testLine = "!start <Cancel>;";
        Expression start = Expression.of(testLine);

        assertThat(start.originalLine(), equalTo(testLine));
    }

    @Test
    @DisplayName("same symbol represent when the expressions are the same, but expressions are not equal")
    void sameSymbol() {
        var start1 = new StartSymbol("symbol", "line");
        var start2 = new StartSymbol("symbol", "anotherLine");
        assertThat(start1.equals(start2), equalTo(false));

        assertThat(start1.representsSameExpressionAs(start2), equalTo(true));
    }

    @Test
    @DisplayName("Same symbol is not the same because the types are different")
    void differentType(){
        var start1 = new StartSymbol("expr", "line");
        var rule = new RuleSymbol("symbol", "expr", "line");

        assertThat(start1.representsSameExpressionAs(rule), equalTo(false));
    }
}