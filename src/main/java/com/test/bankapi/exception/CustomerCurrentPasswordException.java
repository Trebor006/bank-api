package com.test.bankapi.exception;

public class CustomerCurrentPasswordException extends RuntimeException {

    public CustomerCurrentPasswordException(String message) {
        super(message);
    }
}
