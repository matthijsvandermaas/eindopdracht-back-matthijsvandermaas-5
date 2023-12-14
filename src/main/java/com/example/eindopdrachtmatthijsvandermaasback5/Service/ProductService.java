package com.example.eindopdrachtmatthijsvandermaasback5.Service;


import com.example.eindopdrachtmatthijsvandermaasback5.DTO.ProductDto;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.IdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Exceptions.ProductIdNotFoundException;
import com.example.eindopdrachtmatthijsvandermaasback5.Models.Product;
import com.example.eindopdrachtmatthijsvandermaasback5.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = productToProductDto(p);
            productDtos.add(pdto);
        }
        return productDtos;
    }

    public List<ProductDto> getProductsByProductName(String productName) {

        List<Product> products = productRepository.findByProductName(productName);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = new ProductDto();
            productToProductDto(p);
            productDtos.add(pdto);
        }

        return productDtos;
    }


    private ProductDto productToProductDto(Product p) {
        ProductDto pdto = new ProductDto();
        pdto.setId(p.getId());
        pdto.setProductName(p.getProductName());
        pdto.setNameBrewer(p.getNameBrewer());
        pdto.setProductionLocation(p.getProductionLocation());
        pdto.setTast(p.getTast());
        pdto.setType(p.getType());
        pdto.setAlcohol(p.getAlcohol());
        pdto.setIbu(p.getIbu());
        pdto.setColor(p.getColor());
        pdto.setVolume(p.getVolume());
        return pdto;
    }

    private Product productDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setNameBrewer(productDto.getNameBrewer());
        product.setProductionLocation(productDto.getProductionLocation());
        product.setTast(productDto.getTast());
        product.setType(productDto.getType());
        product.setAlcohol(productDto.getAlcohol());
        product.setIbu(productDto.getIbu());
        product.setColor(productDto.getColor());
        product.setVolume(productDto.getVolume());
        return product;
    }

    public ProductDto getProduct(String productName) {
        Optional<Product> product = productRepository.findById(productName);
        if (product.isPresent()) {
            Product p = product.get();
            ProductDto pdto = new ProductDto();
            productToProductDto(p);
            return (pdto);
        } else {
            throw new ProductIdNotFoundException("Product not found with name: " + productName);
        }
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product savedProduct = productRepository.save(productDtoToProduct(productDto));

        return productToProductDto(savedProduct);
    }
//
//    public String deleteProduct(@RequestBody String productName) {
//        if (productRepository.existsById(productName)) {
//            productRepository.deleteById(productName);
//        } else {
//            throw new ProductIdNotFoundException("Product not found with name: " + productName);
//        }
//        return "Product deleted";
//    }

}
