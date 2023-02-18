package com.test.bankapi.exception;

public class MovementNoAllowedException extends RuntimeException {

    public MovementNoAllowedException(String message) {
        super(message);
    }
}
