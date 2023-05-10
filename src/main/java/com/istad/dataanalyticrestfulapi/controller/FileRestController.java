package com.istad.dataanalyticrestfulapi.controller;

import com.istad.dataanalyticrestfulapi.service.FileStorageService;
import com.istad.dataanalyticrestfulapi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file-service")
public class FileRestController {

    // inject service
    @Autowired
    FileStorageService fileStorageService ;
    @PostMapping("/file-upload")
    public Response<?> fileUpload(@RequestParam MultipartFile file){
        String filename = fileStorageService.uploadFile(file);
        return Response.<Object>ok().setPayload(filename) ;
    }
}
