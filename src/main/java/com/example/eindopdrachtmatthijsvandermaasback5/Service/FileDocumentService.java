package com.example.eindopdrachtmatthijsvandermaasback5.Service;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Product;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.FileDocumentRepository;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class FileDocumentService {
   private final FileDocumentRepository fileDocumentRepository;
   private final ProductRepository productRepository;

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


         String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
         System.out.println("Original File Name: " + originalFilename);

         FileDocument fileDocument = new FileDocument();
         fileDocument.setFilename(originalFilename);
         fileDocument.setDocFile(file.getBytes());
         fileDocument.setProduct(product);

         fileDocument = fileDocumentRepository.save(fileDocument);

         System.out.println("File Document saved: " + fileDocument.getFilename());

         product.setImage(fileDocument);
         productRepository.save(product);
         System.out.println("File Document linked to Product");
         System.out.println("Image uploaded successfully");
         System.out.println(fileDocument.getFilename());

         return fileDocument;
      } catch (IOException e) {
         System.err.println("Image upload failed");
         throw new RuntimeException("Image upload failed", e);
      }
   }


   public FileDocument downloadFileDocument(String filename, String productName, HttpServletRequest request){

      FileDocument document = (FileDocument) fileDocumentRepository.findByFilename(filename);

      String mimeType = request.getServletContext().getMimeType(document.getFilename());

      return document;

   }
}
