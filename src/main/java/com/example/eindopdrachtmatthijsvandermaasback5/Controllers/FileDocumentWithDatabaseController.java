package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse.FileUploadResponse;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.FileDocumentService;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
public class FileDocumentWithDatabaseController {
    private static final Logger logger = LoggerFactory.getLogger(FileDocumentWithDatabaseController.class);

    private final FileDocumentService fileDocumentService;
    private ProductService productService;
    private FileDocumentRepository fileDocumentRepository;

    public FileDocumentWithDatabaseController(FileDocumentService fileDocumentService, ProductService productService) {
        this.fileDocumentService = fileDocumentService;
        this.productService = productService;
    }

    @PostMapping("/single/uploadDB/{productName}")
    public FileUploadResponse singleFileUpload(
            @RequestParam("file") MultipartFile file,
            @PathVariable("productName") String productName
    ) throws IOException {
        try {
            logger.info("Received request to upload file for product: {}", productName);
            logger.info("Received file: {}", file.getOriginalFilename());
            logger.info("Received productName: {}", productName);

            FileDocument fileDocument = fileDocumentService.uploadFileDocument(file, productName);
            String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFromDB/")
                    .path(Objects.requireNonNull(file.getOriginalFilename()))
                    .toUriString();

            String contentType = file.getContentType();

            return new FileUploadResponse(productName, url, contentType);
        } catch (Exception e) {
            logger.error("Error uploading file:", e);
            throw new RuntimeException("Error uploading file", e);
        }
    }


    @GetMapping("/downloadFromDB/{filename}/{productName}")
    public ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String filename,@PathVariable String productName, HttpServletRequest request) {
        FileDocument document = fileDocumentService.downloadFileDocument(filename, productName, request);
        String mimeType = request.getServletContext().getMimeType(document.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + document.getFilename())
                .body(Base64.encodeBase64(document.getDocFile(),false));
    }


}



