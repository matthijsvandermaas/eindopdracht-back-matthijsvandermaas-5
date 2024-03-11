package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue
    private Long id;
    @Id
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

//    OneToOne met filedocument
@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
private FileDocument image;
//methodes
//    public Product() {
//        this.image = new ArrayList<>();
//    }
//    public void setFileDocument(FileDocument fileDocument) {
//        if (this.images == null) {
//            this.image = new ArrayList<>();
//        }
//        this.images.add(fileDocument);
//    }
}



