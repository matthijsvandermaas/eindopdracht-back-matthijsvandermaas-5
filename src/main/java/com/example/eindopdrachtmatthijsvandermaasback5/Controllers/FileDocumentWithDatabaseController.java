package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse.FileUploadResponse;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.FileDocumentService;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.ProductService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String productName) throws IOException {
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

//    @GetMapping("/downloadFromDB/{filename}/{productName}")
//    public ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String filename, @PathVariable String productName, HttpServletRequest request) {
//        logger.info("Download request received for filename: {}, productName: {}", filename, productName);
//
//        FileDocument document = fileDocumentService.downloadFileDocument(filename, productName, request);
//
//        if (document == null) {
//            logger.error("FileDocument not found for filename: {} and productName: {}", filename, productName);
//            throw new RuntimeException("FileDocument not found for filename: " + filename + " and productName: " + productName);
//        }
//
//        String mimeType = request.getServletContext().getMimeType(document.getFilename());
//
//        logger.info("Mime type for file {}: {}", document.getFilename(), mimeType);
//
//        byte[] fileContent = Base64.encodeBase64(document.getDocFile(), false);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + document.getFilename())
//                .body(fileContent);
//    }
//@GetMapping("/images/{imageName}")
//public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
//    Resource resource = (Resource) new ClassPathResource("images/" + imageName);
//    Path imagePath = Paths.get(resource.getURI());
//
//    byte[] imageBytes = Files.readAllBytes(imagePath);
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.IMAGE_PNG);
//    headers.setContentLength(imageBytes.length);
//
//    return ResponseEntity.ok().headers(headers).body(imageBytes);
//}



}



