package com.acepero13.research.bnfreplacer.utils.fs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Copier {
    private Copier() {

    }

    public static void createCopyOfUsingTimestampForName(Path sourcePath) throws IOException {
        Path targetPath = getNewFileName(sourcePath);
        Files.copy(sourcePath, targetPath);
        System.out.println("File copied successfully.");
    }

    private static Path getNewFileName(Path sourcePath) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
        String timestamp = "_" + now.format(formatter);

        // Construct the target path with the timestamp appended before the extension
        String newName = sourcePath.getFileName().toString().replaceFirst("(\\.[^.]+)?$", timestamp + "$0");
        return sourcePath.getParent().resolve(newName);
    }

}
