package com.example.eindopdrachtmatthijsvandermaasback5.Exceptions;

public class ProductIdNotFoundException extends RuntimeException {
    public ProductIdNotFoundException(String message) {
        super(message);
    }
}