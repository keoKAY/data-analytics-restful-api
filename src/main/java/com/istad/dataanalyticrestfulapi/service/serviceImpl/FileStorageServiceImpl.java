package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
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
        Path imagesLocation = Paths.get(serverLocation);
        List<File> allFiles = List.of(imagesLocation.toFile().listFiles());

        File deletedFile = allFiles.stream().filter(
                file -> file.getName().equals(filename)
        ).findFirst().orElse(null);

        try{
            if(deletedFile!=null){
                Files.delete(deletedFile.toPath());
                return  "delete file successfully ";
            }else {
                // cannot delete because there is no image
                return "file with "+filename + " doesn't exit ";
            }
        }catch (Exception ex ){
            System.out.println("Error delete file by name : "+ex.getMessage());
            return  "exception occurred! failed to delete file by name ";
        }
    }

    @Override
    public String deleteAllFiles() {
        Path imageLocation = Paths.get(serverLocation);
        File[] files = imageLocation.toFile().listFiles();
        try{
            if(files == null || files.length == 0 ){
                return "There is no files to delete !!";
            }
            for(File file : files ){
                Files.delete(file.toPath());
            }
            return "Successfully delete all files ";

        }catch (Exception ex ){
            ex.printStackTrace();
            System.out.println("Exception Delete all files : "+ex.getMessage());
            return "Failed to delete all files ! Exception occurred ! ";
        }


    }
}
