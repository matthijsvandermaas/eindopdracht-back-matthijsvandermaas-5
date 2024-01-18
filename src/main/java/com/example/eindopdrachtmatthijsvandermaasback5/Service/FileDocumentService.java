package com.example.eindopdrachtmatthijsvandermaasback5.Service;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileDocumentService {

   private final FileDocumentRepository fileDocumentRepository;

   public FileDocumentService(FileDocumentRepository fileDocumentRepository) {
      this.fileDocumentRepository = fileDocumentRepository;
   }

   public FileDocument uploadFileDocument(MultipartFile file) throws IOException {
      String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      FileDocument fileDocument = new FileDocument();
      fileDocument.setFileName(name);
      fileDocument.setDocFile(file.getBytes());

      return fileDocumentRepository.save(fileDocument);
   }

   public FileDocument singleFileDownload(String fileName) {
      return fileDocumentRepository.findByFileName(fileName)
              .orElseThrow(() -> new FileNotFoundException("File not found with name: " + fileName));
   }
}
