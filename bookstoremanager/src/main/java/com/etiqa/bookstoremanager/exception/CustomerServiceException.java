package com.etiqa.bookstoremanager.exception;

public class CustomerServiceException extends RuntimeException {
    public CustomerServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}