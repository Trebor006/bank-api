package com.test.bankapi.service;

import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.entity.Account;

import java.util.List;

public interface AccountServiceInterface {
    List<Account> getAccounts();

    Account getAccountById(Long id);

    Account createAccount(AccountDto accountDto);

    Account updateAccount(Long id, AccountDto accountDto);

    void deleteAccount(Long id);

    void updateAccount(Account account);
}
