package com.test.bankapi.exception;

public class CustomerNotFoundException extends RuntimeException {

    public static final String CUSTOMER_NOT_FOUND_WITH_ID = "Customer Not Found with Id: ";

    public CustomerNotFoundException(Long id) {
        super(CUSTOMER_NOT_FOUND_WITH_ID + id);
    }
}
