package com.test.bankapi.exception;

public class AccountDebitMaxExcededException extends RuntimeException {

    public AccountDebitMaxExcededException(String message) {
        super(message);
    }
}
