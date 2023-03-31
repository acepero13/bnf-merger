package com.acepero13.research.bnfreplacer.plugins;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * A Description
 *
 * @author Alvaro Cepero
 */
class SkipDummySlotsTest {
    private final Preprocessing slots = new SkipDummySlots();

    @Test void skipDummySlot(){
        var line = "(1|2| 3)";
        assertThat(slots.isAllowed(line),equalTo(false));
    }

    @Test void doesNotSkipARegularLine(){
        var line = "!start TEST";
        assertThat(slots.isAllowed(line), equalTo(true));
    }

}
