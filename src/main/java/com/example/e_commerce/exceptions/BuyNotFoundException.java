package com.example.e_commerce.exceptions;

public class BuyNotFoundException extends RuntimeException {
    public BuyNotFoundException(String message) {
        super(message);
    }
}