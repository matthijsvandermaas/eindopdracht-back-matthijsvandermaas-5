package com.example.eindopdrachtmatthijsvandermaasback5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.eindopdrachtmatthijsvandermaasback5.Models")
public class EindopdrachtMatthijsvandermaasBack5Application {

    public static void main(String[] args) {
        SpringApplication.run(EindopdrachtMatthijsvandermaasBack5Application.class, args);
    }

}
