package com.example.e_commerce.exceptions;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException(String message) {
        super(message);
    }
}