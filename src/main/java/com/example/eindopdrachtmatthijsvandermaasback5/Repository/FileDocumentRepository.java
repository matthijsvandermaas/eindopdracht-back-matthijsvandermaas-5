package com.example.eindopdrachtmatthijsvandermaasback5.Repository;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
    @Transactional
    List<FileDocument> findByFilename(String filename);

}
