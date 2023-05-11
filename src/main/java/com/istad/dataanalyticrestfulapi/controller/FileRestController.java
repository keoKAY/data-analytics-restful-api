package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.model.response.FileResponse;
import com.istad.dataanalyticrestfulapi.service.FileStorageService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file-service")
public class FileRestController {

    // inject service
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/file-upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file) {
        try {
            FileResponse response = uploadFile(file);
            return Response.<FileResponse>ok().setPayload(response)
                    .setMessage("Successfully upload a file ");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Failed to upload image " + ex.getMessage());
            return Response.<FileResponse>exception().setMessage("Failed to upload an image ! Exception Occurred ! ");
        }
    }


    // helper method
    @PostMapping("/multiple-file-upload")
    public Response<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            List<FileResponse> responses = Arrays.stream(files)
                    .map(this::uploadFile)
                    .toList();
            return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("Successfully upload multiple files ");

        } catch (Exception ex) {
            System.out.println("Exception happened! " + ex.getMessage());
            return Response.<List<FileResponse>>exception().setMessage(" Failed to upload multiple images ! Exception occurred! ");
        }
    }
    // for delete single and multiple files

    @DeleteMapping("/delete-file/{filename}")
    public String deleteSingleFile(@PathVariable String filename) {
        // method definition will be written later.
        String result = fileStorageService.deleteFileByName(filename);
        return result;
    }

    @DeleteMapping("/delete-all-files")
    public String deleteAllFiles() {
        String result = fileStorageService.deleteAllFiles();
        return result;
    }


    private FileResponse uploadFile(MultipartFile file) {
        String filename = fileStorageService.uploadFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file")
                .path(filename)
                .toUriString();

        return new FileResponse().setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(file.getContentType())
                .setSize(file.getSize())
                ;
    }


}
