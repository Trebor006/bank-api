package com.test.bankapi.exception;

public class MovementNotFoundException extends RuntimeException {

    public MovementNotFoundException(Long id) {
        super("Movement Not Found with Id: " + id);
    }
}
