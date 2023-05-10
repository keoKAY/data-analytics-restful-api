package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final String serverLocation = "src/main/resources/images/";
    Path fileLocationStorage;

    public FileStorageServiceImpl() {
        fileLocationStorage = Paths.get(serverLocation);
        try {
            if (!Files.exists(fileLocationStorage)) {
                Files.createDirectories(fileLocationStorage);
            } else {
                System.out.println("Directory is already existed ! ");
            }
        } catch (Exception ex) {
            System.out.println("Error creating directory : " + ex.getMessage());
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        // check to see if file is empty
        String[] fileCompartments = filename.split("\\.");
        filename = UUID.randomUUID() + "." + fileCompartments[1];
        Path resolvedPath = fileLocationStorage.resolve(filename);
        try {
            Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception ex) {
            return ex.getMessage();
        }

    }

    @Override
    public String deleteFileByName(String filename) {
        return null;
    }

    @Override
    public String deleteAllFiles() {
        return null;
    }
}
