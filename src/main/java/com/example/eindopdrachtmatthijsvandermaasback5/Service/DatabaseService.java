package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.DocFileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class DatabaseService {
   private final DocFileRepository doc;

    public DatabaseService(DocFileRepository doc){
        this.doc = doc;
    }

    public FileDocument uploadFileDocument(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(name);
        fileDocument.setDocFile(file.getBytes());

        doc.save(fileDocument);

        return fileDocument;

    }

    public FileDocument singleFileDownload(String fileName, HttpServletRequest request){

        FileDocument document = doc.findByFileName(fileName);

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

        return document;

    }

}