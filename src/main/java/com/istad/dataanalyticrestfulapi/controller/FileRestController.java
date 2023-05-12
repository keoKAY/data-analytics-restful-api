package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.model.response.FileResponse;
import com.istad.dataanalyticrestfulapi.service.FileStorageService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file-service")
public class FileRestController {

    // inject service
    @Autowired
    FileStorageService fileStorageService;

    // allowed extension (jpg ,  png)
    private final List<String > ALLOWED_EXTENSIONS = List.of("jpg","png","jpeg");
    private final long MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
    // size profile image

    @PostMapping("/file-upload")
    public Response<FileResponse> fileUpload( @RequestParam("file") MultipartFile file) {

              FileResponse response = uploadFile(file);
              return Response.<FileResponse>ok().setPayload(response)
                      .setMessage("Successfully upload a file ");

    }
    // helper method
    @PostMapping("/multiple-file-upload")
    public Response<List<FileResponse>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

            List<FileResponse> responses = Arrays.stream(files)
                    .map(this::uploadFile)
                    .toList();
            return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("Successfully upload multiple files ");


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

    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename,HttpServletRequest request) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(filename);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException exception){
            System.out.println("Error Getting content type is : "+exception.getMessage());
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }
    private FileResponse uploadFile(MultipartFile file) {
        // file is empty
        if(file.isEmpty())
            throw new IllegalArgumentException("Files cannot be empty");

        // files size
        if(file.getSize()> MAX_FILE_SIZE)
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);
        // extensions

        // the old way
//        String[] fileParts = file.getOriginalFilename().split("\\.");
//        String extension = fileParts[1];

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())){
            throw new IllegalArgumentException("Allowed Extension are 'jpg', 'jpeg', 'png' ");
        }

        String filename = fileStorageService.uploadFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file/")
                .path(filename)
                .toUriString();

        return new FileResponse().setFilename(filename)
                .setFileDownloadUri(fileDownloadUri)
                .setFileType(file.getContentType())
                .setSize(file.getSize())
                ;
    }


}
