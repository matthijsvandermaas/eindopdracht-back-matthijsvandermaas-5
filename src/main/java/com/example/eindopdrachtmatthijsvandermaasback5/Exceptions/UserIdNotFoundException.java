package com.example.eindopdrachtmatthijsvandermaasback5.Exceptions;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(String message) {
        super(message);
    }
}