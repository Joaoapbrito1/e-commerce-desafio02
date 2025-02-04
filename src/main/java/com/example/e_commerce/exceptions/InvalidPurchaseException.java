package com.example.e_commerce.exceptions;

public class InvalidPurchaseException extends RuntimeException {
    public InvalidPurchaseException(String message) {
        super(message);
    }
}