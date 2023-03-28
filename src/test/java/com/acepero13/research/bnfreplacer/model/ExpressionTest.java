package com.acepero13.research.bnfreplacer.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExpressionTest {

    @Test
    void commentExpression() {
        String testLine = "this is a comment;";
        var comment = Expression.of(testLine);
        var sameComment = Expression.of(testLine);
        assertThat(comment, equalTo(sameComment));
    }

    @Test
    void instructionExpression() {
        String line = "#BNF+EM V2.1;";

        var expr = Expression.of(line);

        assertThat(expr.originalLine(), equalTo(line));
    }


}