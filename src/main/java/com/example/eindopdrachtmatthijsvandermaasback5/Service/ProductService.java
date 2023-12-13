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
            ProductDto pdto = productToProductDTO(p);
            productDtos.add(pdto);
        }
        return productDtos;
    }

    public List<ProductDto> getProductsByProductName(String productName) {

        List<Product> products = productRepository.findByProductName(productName);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product p : products) {
            ProductDto pdto = new ProductDto();
            productToProductDTO(p);
            productDtos.add(pdto);
        }

        return productDtos;
    }


    private ProductDto productToProductDTO(Product p) {
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

    private Product  productDTOToProduct(ProductDto productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductName(productDTO.getProductName());
        product.setNameBrewer(productDTO.getNameBrewer());
        product.setProductionLocation(productDTO.getProductionLocation());
        product.setTast(productDTO.getTast());
        product.setType(productDTO.getType());
        product.setAlcohol(productDTO.getAlcohol());
        product.setIbu(productDTO.getIbu());
        product.setColor(productDTO.getColor());
        product.setVolume(productDTO.getVolume());
        return product;
    }

    public ProductDto getProduct(String productName) {
        Optional<Product> product = productRepository.findById(productName);
        if (product.isPresent()) {
            Product p = product.get();
            ProductDto pdto = new ProductDto();
            productToProductDTO(p);
            return (pdto);
        } else {
            throw new ProductIdNotFoundException("Product not found with name: " + productName);
        }
    }

    public ProductDto createProduct(ProductDto productDTO) {
        Product savedProduct = productRepository.save(productDTOToProduct(productDTO));

        return productToProductDTO(savedProduct);
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
