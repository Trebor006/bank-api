package com.test.bankapi.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Customer Not Found with Id: " + id);
    }
}
