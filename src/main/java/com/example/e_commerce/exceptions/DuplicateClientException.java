package com.example.e_commerce.exceptions;

public class DuplicateClientException extends RuntimeException {
    public DuplicateClientException(String message) {
        super(message);
    }
}

