package com.example.eindopdrachtmatthijsvandermaasback5.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class FileDocument {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String fileName;

    @Lob
    @NotEmpty
    @Column(columnDefinition = "LONGBLOB")
    private byte[] docFile;

    // ManyToOne met product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}