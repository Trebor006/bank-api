package com.test.bankapi.service;

import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.entity.Account;
import com.test.bankapi.entity.Customer;
import com.test.bankapi.exception.AccountNotFoundException;
import com.test.bankapi.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountService implements AccountServiceInterface {

    private final AccountRepository accountRepository;
    private final CustomerServiceInterface customerService;

    @Setter
    @Value("${max.daily.debit.amount.default}")
    private String maxDailyDebitAmountDefault;

    @Override
    public List<Account> getAccounts() {
        var accountList = accountRepository.findAll();

        return accountList;
    }

    @Override
    public Account getAccountById(Long id) {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        return account;
    }

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) {
        Customer customer = customerService.getCustomerById(accountDto.getCustomerId());

        var account = Account.builder()
                .customer(customer)
                .number(accountDto.getNumber())
                .type(accountDto.getType())
                .maxDailyDebitAmount(Double.valueOf(maxDailyDebitAmountDefault))
                .initialBalance(accountDto.getInitialBalance())
                .balance(accountDto.getBalance())
                .status(accountDto.getStatus())
                .build();

        account = accountRepository.saveAndFlush(account);

        return account;
    }

    @Override
    @Transactional
    public Account updateAccount(Long id, AccountDto accountDto) {
        Customer customer = customerService.getCustomerById(accountDto.getCustomerId());

        var account = Account.builder()
                .customer(customer)
                .number(accountDto.getNumber())
                .type(accountDto.getType())
                .maxDailyDebitAmount(Double.valueOf(maxDailyDebitAmountDefault))
                .initialBalance(accountDto.getInitialBalance())
                .balance(accountDto.getBalance())
                .status(accountDto.getStatus())
                .build();

        return accountRepository.saveAndFlush(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        //todo Cambiar el eliminar fisico por un cambio de estado
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }
}
