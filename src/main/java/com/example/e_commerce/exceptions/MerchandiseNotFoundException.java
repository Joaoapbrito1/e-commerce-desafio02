package com.example.e_commerce.exceptions;

public class MerchandiseNotFoundException extends RuntimeException {
    public MerchandiseNotFoundException(String message) {
        super(message);
    }
}