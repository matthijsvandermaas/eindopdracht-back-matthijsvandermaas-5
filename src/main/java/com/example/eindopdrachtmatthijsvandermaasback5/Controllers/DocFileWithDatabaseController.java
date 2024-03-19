package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;


import com.example.eindopdrachtmatthijsvandermaasback5.FileUploadResponse.FileUploadResponse;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.DocFileservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@CrossOrigin
@RestController
@RequestMapping("/fileDocument")
public class DocFileWithDatabaseController {

    private final DocFileservice docFileservice;

    public DocFileWithDatabaseController(DocFileservice docFileservice) {
        this.docFileservice = docFileservice;
    }


    @PostMapping("single/uploadDb")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {


        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        FileDocument fileDocument = docFileservice.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFromDB/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFilename(), url, contentType );
    }

    //    get for single download
    @GetMapping("/downloadFromDB/{fileName}")
    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        FileDocument document = docFileservice.singleFileDownload(fileName, request);


        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFilename()).body(document.getDocFile());
    }

    //    post for multiple uploads to database
    @PostMapping("/multiple/upload/db")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile [] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files selected");
        }

        return docFileservice.createMultipleUpload(files);

    }

    @GetMapping("zipDownload/db")
    public void zipDownload(@RequestBody String[] files, HttpServletResponse response) throws IOException {

        docFileservice.getZipDownload(files, response);

    }

    @GetMapping("/getAll/db")
    public Collection<FileDocument> getAllFromDB(){
        return docFileservice.getALlFromDB();
    }
}






