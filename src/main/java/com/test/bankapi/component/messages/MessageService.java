package com.test.bankapi.component.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageService implements MessageServiceInterface {
    private final MessageSource messageSource;

    @Override
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
