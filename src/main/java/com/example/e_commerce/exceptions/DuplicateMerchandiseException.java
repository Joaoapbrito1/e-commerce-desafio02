package com.example.e_commerce.exceptions;

public class DuplicateMerchandiseException extends RuntimeException {
    public DuplicateMerchandiseException(String message) {
        super(message);
    }
}