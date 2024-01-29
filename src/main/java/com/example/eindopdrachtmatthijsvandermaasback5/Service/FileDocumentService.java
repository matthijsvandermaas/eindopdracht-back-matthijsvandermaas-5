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

   private ProductRepository productRepository;

   public FileDocumentService(FileDocumentRepository fileDocumentRepository, ProductRepository productRepository) {
      this.fileDocumentRepository = fileDocumentRepository;
      this.productRepository = productRepository;
   }

   public FileDocument uploadFileDocument(@RequestPart("file") MultipartFile file, String entityName) throws IOException {
      List<Product> productList = productRepository.findByProductName(entityName);
      if (productList.isEmpty()) {
         throw new RuntimeException("Product not found for name: " + entityName);
      }
      Product product = productList.get(0);
      product.setImages(null);
      String originalFileName  = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

      FileDocument fileDocument = new FileDocument();
      fileDocument.setFileName(originalFileName );
      fileDocument.setDocFile(file.getBytes());
      //fileDocument.setProduct(product);

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
