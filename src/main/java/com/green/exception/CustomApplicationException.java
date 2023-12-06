package com.green.exception;

public class CustomApplicationException extends RuntimeException {
    public CustomApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}