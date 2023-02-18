package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AccountDtoMapperService implements AccountDtoMapperServiceInterface {

    @Override
    public List<AccountDto> mapFromEntity(List<Account> account) {
        return account.stream()
                .map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto mapFromEntity(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .customerId(account.getCustomer().getCustomerId())
                .number(account.getNumber())
                .type(account.getType())
                .initialBalance(account.getInitialBalance())
                .balance(account.getBalance())
                .status(account.getStatus())
                .build();
    }
}
