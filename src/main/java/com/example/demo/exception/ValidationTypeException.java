package com.example.demo.exception;

public class ValidationTypeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ValidationTypeException(String message) {
        super(message);
    }

    public ValidationTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
