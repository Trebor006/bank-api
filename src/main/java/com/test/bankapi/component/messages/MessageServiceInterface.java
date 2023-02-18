package com.test.bankapi.component.messages;

public interface MessageServiceInterface {

    String getMessage(String key);

    String getMessage(String key, Object[] args);
}
