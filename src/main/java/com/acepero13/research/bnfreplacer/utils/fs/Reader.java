package com.acepero13.research.bnfreplacer.utils.fs;

import java.io.*;
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {

                sb.append(line);
                if(line.endsWith(";")) {
                    lines.add(sb.toString());
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            LOGGER.warning("Could not read from inputStream");
        }

        return lines;
    }

    public static List<String> from(Path path) {
        try {
            return from(Files.newInputStream(path));
        } catch (IOException e) {
            LOGGER.warning("Could not read from file: " + path);
            return new ArrayList<>();
        }
    }
}
