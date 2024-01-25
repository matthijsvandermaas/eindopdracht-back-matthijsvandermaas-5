package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse.FileUploadResponse;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.FileDocumentService;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin
@RestController
public class UploadDownloadWithDatabaseController {

    private final FileDocumentService fileDocumentService;
    private final ProductService productService;

    public UploadDownloadWithDatabaseController(FileDocumentService fileDocumentService, ProductService productService) {
        this.fileDocumentService = fileDocumentService;
        this.productService = productService;
    }

    @PostMapping("/single/uploadDB")
    public FileUploadResponse singleFileUpload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("productName") String productName
    ) throws IOException {

        FileDocument fileDocument = fileDocumentService.uploadFileDocument(file, productName);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFromDB/")
                .path(Objects.requireNonNull(file.getOriginalFilename()))
                .toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType);
    }

    @GetMapping("/downloadFromDB/{fileName}")
    public ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
        FileDocument document = fileDocumentService.singleFileDownload(fileName, request);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + document.getFileName())
                .body(document.getDocFile());
    }
}
