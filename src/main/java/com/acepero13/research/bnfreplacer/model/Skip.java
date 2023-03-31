package com.acepero13.research.bnfreplacer.model;

/**
 * A Description
 *
 * @author Alvaro Cepero
 */
public record Skip() implements Expression{
    @Override
    public String originalLine() {
        return "";
    }

    @Override
    public String symbol() {
        return "";
    }

    @Override
    public String expression() {
        return "";
    }
}
