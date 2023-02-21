package com.test.bankapi.exception;

public class MovementNotFoundException extends RuntimeException {

    public static final String MOVEMENT_NOT_FOUND_WITH_ID = "Movement Not Found with Id: ";

    public MovementNotFoundException(Long id) {
        super(MOVEMENT_NOT_FOUND_WITH_ID + id);
    }
}
