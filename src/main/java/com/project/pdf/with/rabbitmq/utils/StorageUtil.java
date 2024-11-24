package com.project.pdf.with.rabbitmq.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageUtil {
    private static final String BASE_DIRECTORY = "uploads";

    public static String saveFile(String fileName, byte[] fileData) throws IOException {
        File baseDir = new File(BASE_DIRECTORY);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }

        Path filePath = Paths.get(BASE_DIRECTORY, fileName);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(fileData);
        }

        return "/files/" + fileName;
    }
}
