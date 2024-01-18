package com.example.eindopdrachtmatthijsvandermaasback5.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class FileDocumentDto {

    private String fileName;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] docFile;
}
