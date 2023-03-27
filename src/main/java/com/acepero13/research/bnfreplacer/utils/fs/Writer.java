package com.acepero13.research.bnfreplacer.utils.fs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public final class Writer {
    private Writer() {

    }

    public static void save(List<String> lines, Path destinationFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile.toString(), StandardCharsets.UTF_8))) {
            writeAll(lines, writer);
        }
    }

    private static void writeAll(List<String> lines, BufferedWriter writer) throws IOException {
        for (String line : lines) {
            writer.write(line);
            writer.newLine(); // Write each string on a new line
        }
    }
}
