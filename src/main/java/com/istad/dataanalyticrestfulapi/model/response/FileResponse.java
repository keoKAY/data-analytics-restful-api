package com.istad.dataanalyticrestfulapi.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true )
public class FileResponse {
    private String filename;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
