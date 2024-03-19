package com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse;
import lombok.Data;

@Data
public class FileUploadResponse {

    String filename;
    String contentType;
    String url;

    public FileUploadResponse(String filename, String url, String contentType) {
        this.filename = filename;
        this.contentType = contentType;
        this.url = url;
    }
}