package com.example.eindopdrachtmatthijsvandermaasback5.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private String nameBrewer;
    private String productionLocation;
    private String tast;
    private String type;
    private String alcohol;
    private String ibu;
    private String color;
    private String volume;

//    OneToMany met filedocument
@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
private List<FileDocument> images = new ArrayList<>();


    public void setFileDocument(FileDocument fileDocument) {
        images.add(fileDocument);
        fileDocument.setProduct(this);
    }
}



