package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue
    private Long id;
    @Id
    @Column(unique = true)
    private String productName;
    private String nameBrewer;
    private String productionLocation;
    private String tast;
    private String type;
    private String alcohol;
    private String ibu;
    private String color;
    private String volume;
//    @Lob
//    private String filename;

//    OneToOne met file-document
@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private FileDocument image;

}



