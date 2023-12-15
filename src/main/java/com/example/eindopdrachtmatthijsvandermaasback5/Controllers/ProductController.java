package com.example.eindopdrachtmatthijsvandermaasback5.Controllers;

import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProductDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.IdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.ProductIdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.engine.jdbc.mutation.spi.Binding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    ;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> pdto = productService.getAllProducts();
        return ResponseEntity.ok(pdto);
    }


    @GetMapping("/productName")
    public ResponseEntity<List<ProductDto>> getProductsByProductName(@RequestParam String productName) {
        List<ProductDto> pdto = productService.getProductsByProductName(productName);
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto newProduct = productService.createProduct(productDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productName}") // voegt product toe aan de lijst en vervangt het niet??
    public ResponseEntity<ProductDto> updateProduct(@PathVariable ("productName") String productName, @Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto updatedProduct = productService.updateProduct(productName, productDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductIdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//werkt niet
    @DeleteMapping("/{productName}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable ("productName") String productName) {
        try {
            productService.deleteProduct(productName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProductIdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
