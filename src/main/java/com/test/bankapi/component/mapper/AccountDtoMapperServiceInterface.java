package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.entity.Account;

import java.util.List;

public interface AccountDtoMapperServiceInterface {
    List<AccountDto> mapFromEntity(List<Account> account);

    AccountDto mapFromEntity(Account account);
}
