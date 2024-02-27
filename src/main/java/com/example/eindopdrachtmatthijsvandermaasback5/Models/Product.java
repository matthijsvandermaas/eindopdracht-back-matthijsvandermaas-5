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

//    OneToMany met filedocument
@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
private List<FileDocument> images = new ArrayList<>();
//methodes
    public Product() {
        this.images = new ArrayList<>();
    }
    public void setFileDocument(FileDocument fileDocument) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(fileDocument);
    }
}



