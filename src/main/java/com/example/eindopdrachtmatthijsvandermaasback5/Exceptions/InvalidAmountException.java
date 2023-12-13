package com.example.eindopdrachtmatthijsvandermaasback5.Exceptions;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(String message) {
        super(message);
    }
}
