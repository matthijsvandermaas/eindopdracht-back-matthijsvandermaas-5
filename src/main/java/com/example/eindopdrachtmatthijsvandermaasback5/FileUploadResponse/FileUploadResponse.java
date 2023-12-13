package com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse;
import lombok.Data;

@Data
public class FileUploadResponse {

    String fileName;
    String contentType;
    String url;

    public FileUploadResponse(String fileName, String url, String contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.url = url;
    }
}