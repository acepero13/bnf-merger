package com.acepero13.research.bnfreplacer.plugins;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Description
 *
 * @author Alvaro Cepero
 */
public class SkipDummySlots implements Preprocessing {
    private static final Pattern PATTERN = Pattern.compile("\\s*\\(\\s*1\\s*\\|\\s*2\\s*\\|\\s*3\\s*\\)\\s*");

    @Override
    public boolean isAllowed(String line) {
        Matcher matcher = PATTERN.matcher(line);
        return !matcher.find();
    }

}
