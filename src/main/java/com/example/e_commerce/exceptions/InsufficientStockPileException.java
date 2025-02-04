package com.example.e_commerce.exceptions;

public class InsufficientStockPileException extends RuntimeException {
    public InsufficientStockPileException(String message) {
        super(message);
    }
}