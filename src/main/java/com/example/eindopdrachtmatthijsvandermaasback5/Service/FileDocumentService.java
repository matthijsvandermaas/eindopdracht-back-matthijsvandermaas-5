package com.example.eindopdrachtmatthijsvandermaasback5.Service;

import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.IdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Product;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileDocumentService {

   private final FileDocumentRepository fileDocumentRepository;

   private ProductRepository productRepository;

   public FileDocumentService(FileDocumentRepository fileDocumentRepository, ProductRepository productRepository) {
      this.fileDocumentRepository = fileDocumentRepository;
      this.productRepository = productRepository;
   }

   public FileDocument uploadFileDocument(MultipartFile file, String productName) throws IOException {
      Product product = (Product) productRepository.findByProductName(productName);

      String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
      FileDocument fileDocument = new FileDocument();
      fileDocument.setFileName(name);
      fileDocument.setDocFile(file.getBytes());
      fileDocumentRepository.save(fileDocument);

      product.setFileDocument(fileDocument);

      productRepository.save(product);

      return fileDocumentRepository.save(fileDocument);
   }

   public FileDocument singleFileDownload(String fileName, HttpServletRequest request){

      FileDocument document = (FileDocument) fileDocumentRepository.findByFileName(fileName);

      String mimeType = request.getServletContext().getMimeType(document.getFileName());

      return document;

   }
}
