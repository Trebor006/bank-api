package com.test.bankapi.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Account Not Found with Id: " + id);
    }
}
