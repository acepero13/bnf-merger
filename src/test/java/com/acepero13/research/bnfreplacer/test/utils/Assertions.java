package com.acepero13.research.bnfreplacer.test.utils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A Description
 *
 * @author Alvaro Cepero
 */
public class Assertions {
    private Assertions(){

    }

    public static void assertListsAreEqual(List<String> expected, List<String> result) {
        assertThat(result.size(), equalTo(result.size()));

        for (int i = 0; i < expected.size(); i++) {
            var expectedValue = expected.get(i);
            var actualValue = result.get(i);
            assertEquals(expectedValue, actualValue);
        }
    }
}
