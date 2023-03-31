package com.acepero13.research.bnfreplacer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

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

    @Test
    @DisplayName("<ADJECTIVE>:( cheap) | ( light); -> RuleExpression")
    void bug_shouldRecognizeExpression(){
        String line = "<ADJECTIVE>:( cheap) | ( light);";
        var expr = Expression.of(line);
        assertThat(expr, instanceOf(RuleSymbol.class));
    }



}
