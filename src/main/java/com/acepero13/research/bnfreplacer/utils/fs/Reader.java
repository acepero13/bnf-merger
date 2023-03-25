package com.acepero13.research.bnfreplacer.utils.fs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Reader {

    private Reader() {
    }

    public static final Logger LOGGER = Logger.getLogger(Reader.class.getName());

    public static List<String> from(InputStream inputStream) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            LOGGER.warning("Could not read from inputStream");
        }

        return lines;
    }

    public static List<String> from(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.warning("Could not read from file: " + path);
            return new ArrayList<>();
        }
    }
}
