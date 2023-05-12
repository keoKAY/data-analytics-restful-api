package com.istad.dataanalyticrestfulapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    // 1. upload file
    // 2. delete file by name
    // 3. delete all files

    String uploadFile(MultipartFile file ) ;
    String deleteFileByName(String filename);
    String deleteAllFiles();

    // load resource for downloading
    Resource loadFileAsResource(String filename) throws Exception;

}
