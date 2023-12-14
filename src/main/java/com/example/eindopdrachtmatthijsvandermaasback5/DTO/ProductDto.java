package com.example.eindopdrachtmatthijsvandermaasback5.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    @NotEmpty(message = "productName cannot be empty")
    private String productName;
    @NotEmpty(message = "BrewerName cannot be empty")
    private String nameBrewer;
    @NotEmpty(message = "productionLocation cannot be empty")
    private String productionLocation;
    @NotEmpty(message = "tast cannot be empty")
    private String tast;
    @NotEmpty(message = "type cannot be empty")
    private String type;
    @NotNull(message = "alcohol cannot be empty")
    private String alcohol;
    @NotEmpty(message = "ibu cannot be empty")
    private String ibu;
    @NotEmpty(message = "color cannot be empty")
    private String color;
    @NotEmpty(message = "volume cannot be empty")
    private String volume;

}
