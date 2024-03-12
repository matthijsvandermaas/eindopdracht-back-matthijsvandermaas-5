package com.example.eindopdrachtmatthijsvandermaasback5.Repository;


import com.example.eindopdrachtmatthijsvandermaasback5.Models.Product;
import jdk.jfr.Relational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductName(String productName);
    void deleteByProductName(String s);


}
