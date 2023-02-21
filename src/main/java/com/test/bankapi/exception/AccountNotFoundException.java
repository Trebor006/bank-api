package com.test.bankapi.exception;

public class AccountNotFoundException extends RuntimeException {

    public static final String ACCOUNT_NOT_FOUND_WITH_ID = "Account Not Found with Id: ";

    public AccountNotFoundException(Long id) {
        super(ACCOUNT_NOT_FOUND_WITH_ID + id);
    }
}
