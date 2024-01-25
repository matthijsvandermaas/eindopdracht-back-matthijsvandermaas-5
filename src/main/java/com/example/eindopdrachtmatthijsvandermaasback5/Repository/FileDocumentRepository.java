package com.example.eindopdrachtmatthijsvandermaasback5.Repository;

import com.example.eindopdrachtmatthijsvandermaasback5.Models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FileDocumentRepository extends JpaRepository<FileDocument, Long> {
    List<FileDocument> findByFileName(String fileName);

}
