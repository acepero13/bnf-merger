package com.acepero13.research.bnfreplacer;

import com.acepero13.research.bnfreplacer.core.Replacer;
import com.acepero13.research.bnfreplacer.core.reports.model.Report;
import com.acepero13.research.bnfreplacer.utils.fs.Copier;
import com.acepero13.research.bnfreplacer.utils.fs.Writer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Main {
    private final Path destination;
    private final Replacer replacer;

    private Main(Path source, Path destination) {
        this.destination = Objects.requireNonNull(destination);
        Objects.requireNonNull(source);
        this.replacer = Replacer.of(source, destination);
    }

    public void execute() throws IOException {
        List<String> result = replacer.replaceAll();
        this.report(replacer.report());
        saveCopy();
        saveContentToFile(result);

    }

    private void saveContentToFile(List<String> result) throws IOException {
        Writer.save(result, destination);
    }

    private void saveCopy() throws IOException {
        Copier.createCopyOfUsingTimestampForName(destination);
    }

    private void report(Report report) {
        System.out.println("\n\n----------------------- REPORT --------------------\n");
        System.out.println("Number of insertions: " + report.totalInsertions());
        System.out.println("Number of updates: " + report.totalUpdates());
        System.out.println("Number of changes: " + report.totalChanges() + "\n");

        report.forEach(System.out::println);
        System.out.println("\n\n ------------ Finished --------------------- \n");
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        if (args.length != 2) {
            throw new IllegalAccessException("Expected two arguments, source and destination but got: " + args.length);
        }
        new Main(Path.of(args[0]), Path.of(args[1])).execute();
    }
}
