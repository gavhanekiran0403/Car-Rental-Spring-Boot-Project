package com.crms.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private final static String uploadDir = "uploads/";

    public static String saveImage(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        // Validate file type
        String contentType = file.getContentType();
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            throw new RuntimeException("Only JPG and PNG images are allowed");
        }

        // Limit file size (2MB)
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new RuntimeException("File size must be less than 2MB");
        }

        // Safe unique file name
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID() + extension;

        Path path = Paths.get(uploadDir + fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return fileName;
    }

    // Delete image
    public static void deleteImage(String fileName) {
        try {
            if (fileName != null) {
                Path path = Paths.get(uploadDir + fileName);
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image");
        }
    }
}