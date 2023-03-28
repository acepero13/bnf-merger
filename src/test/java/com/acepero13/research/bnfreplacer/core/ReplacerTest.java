package com.acepero13.research.bnfreplacer.core;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ReplacerTest {
    @Test
    void destinationIsEmpty() {
        List<String> source = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;");
        var replacer = Replacer.of(source, new ArrayList<>());
        var result = replacer.replaceAll();

        assertThat(result, equalTo(source));
    }

    @Test
    void sourceIsEmpty() {
        List<String> destination = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;");
        var replacer = Replacer.of(new ArrayList<>(), destination);
        var result = replacer.replaceAll();

        assertThat(result, equalTo(destination));
    }

    @Test
    void updatesOneValueBecauseTheExpressionIsDifferent() {
        List<String> source = List.of("!start <AskPrice>;", "<AskPrice>:\t Was ist der Preis;");
        List<String> destination = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;", "!start <No>;", "<No>:\t ( nein| nee| no);");
        var replacer = Replacer.of(source, destination);
        var result = replacer.replaceAll();

        List<String> expected = List.of("!start <AskPrice>;", "<AskPrice>:\t Was ist der Preis;", "!start <No>;", "<No>:\t ( nein| nee| no);");

        assertThat(result, equalTo(expected));
    }

    @Test
    void insertsOneValueBecauseSymbolIsDifferent() {
        List<String> source = List.of("!start <AskPrice2>;");
        List<String> destination = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;", "!start <No>;", "<No>:\t ( nein| nee| no);");
        var replacer = Replacer.of(source, destination);
        var result = replacer.replaceAll();

        List<String> expected = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;", "!start <No>;", "<No>:\t ( nein| nee| no);", "!start <AskPrice2>;");

        assertThat(result, equalTo(expected));
    }

    @Test void reportsInsertion(){
        List<String> source = List.of("!start <AskPrice2>;");
        List<String> destination = List.of("!start <AskPrice>;", "<AskPrice>:\t Wie viel kostet ( es| das)| Was ist der Preis;", "!start <No>;", "<No>:\t ( nein| nee| no);");
        var replacer = Replacer.of(source, destination);
        replacer.replaceAll();
        var report = replacer.report();

        assertThat(report.totalInsertions(), equalTo(1));
        assertThat(report.totalChanges(), equalTo(1));
        assertThat(report.totalUpdates(), equalTo(0));
    }

    @Test
    void onlyOneGrammarIsAllowed() {
        List<String> source = List.of("!grammar AnswerToPropertiesGrammar;");
        List<String> destination = List.of("!grammar DA;");

        var replacer = Replacer.of(source, destination);
        var result = replacer.replaceAll();
        assertThat(result, equalTo(destination));
    }

    @Test void onlyOneLanguageAllowed(){
        List<String> source = List.of("!language \"English\";");
        List<String> destination = List.of("!language \"German\";");

        var replacer = Replacer.of(source, destination);
        var result = replacer.replaceAll();
        assertThat(result, equalTo(destination));
    }

    @Test void onlyOnePragmaAllowed(){
        List<String> source = List.of("#BNF+EM V2.1;");
        List<String> destination = List.of("#BNF+EM V2.2;");

        var replacer = Replacer.of(source, destination);
        var result = replacer.replaceAll();
        assertThat(result, equalTo(destination));
    }

}