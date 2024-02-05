package com.example.eindopdrachtmatthijsvandermaasback5.Service;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Product;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class FileDocumentService {
   private final FileDocumentRepository fileDocumentRepository;

   private ProductRepository productRepository;

   public FileDocumentService(FileDocumentRepository fileDocumentRepository, ProductRepository productRepository) {
      this.fileDocumentRepository = fileDocumentRepository;
      this.productRepository = productRepository;
   }

   public FileDocument uploadFileDocument(@RequestPart("file") MultipartFile file, String productName) {
      try {
         List<Product> productList = productRepository.findByProductName(productName);
         if (productList.isEmpty()) {
            throw new RuntimeException("Product not found for name: " + productName);
         }
         Product product = productList.get(0);
         FileDocument fileDocument = null;
         product.getImages().add(fileDocument);
         String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

         fileDocument = new FileDocument();
         fileDocument.setFileName(originalFileName);
         fileDocument.setDocFile(file.getBytes());

         fileDocumentRepository.save(fileDocument);

         product.setFileDocument(fileDocument);

         productRepository.save(product);
         System.out.println("Image uploaded successfully");
         System.out.println(originalFileName);
         System.out.println(fileDocument.getFileName());
         return fileDocumentRepository.save(fileDocument);
      } catch (IOException e) {
         System.err.println("Image upload failed");
         throw new RuntimeException("Image upload failed", e);
      }
   }


   public FileDocument singleFileDownload(String fileName, HttpServletRequest request){

      FileDocument document = (FileDocument) fileDocumentRepository.findByFileName(fileName);

      String mimeType = request.getServletContext().getMimeType(document.getFileName());

      return document;

   }
}
