package com.example.eindopdrachtmatthijsvandermaasback5.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.List;

@Data
@Entity
@Table(name = "file_document")
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;


    @NotEmpty
    private String filename;

    @Lob
    @NotEmpty
    private byte[] docFile;

    // OneToOne met product
    @OneToOne
    @JoinColumn(name = "productName")
    private Product product;
}

