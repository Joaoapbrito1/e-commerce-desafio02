package com.example.e_commerce.exceptions;

public class InvalidBuyException extends RuntimeException {
    public InvalidBuyException(String message) {
        super(message);
    }
}