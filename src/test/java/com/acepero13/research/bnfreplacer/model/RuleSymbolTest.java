package com.acepero13.research.bnfreplacer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class RuleSymbolTest {
    @Test
    @DisplayName("reads expression symbol")
    void expr() {
        String testLine = "<EntryConversationGrammar_1>:	 <newNewThingsSearch>;";
        var expected = new RuleSymbol("EntryConversationGrammar_1", "<newNewThingsSearch>", testLine);

        var symbol = Expression.of(testLine);
        assertThat(symbol, equalTo(expected));

    }
}