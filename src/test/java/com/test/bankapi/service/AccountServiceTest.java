package com.test.bankapi.service;

import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.entity.Account;
import com.test.bankapi.entity.Customer;
import com.test.bankapi.enums.AccountType;
import com.test.bankapi.enums.StatusEnum;
import com.test.bankapi.exception.AccountNotFoundException;
import com.test.bankapi.repository.AccountRepository;
import com.test.bankapi.service.AccountService;
import com.test.bankapi.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AccountServiceTest {
    @Value("${max.daily.debit.amount.default}")
    private String maxDailyDebitAmountDefault;

    @Autowired
    private Environment env;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    public void setUp() {
        accountService.setMaxDailyDebitAmountDefault(env.getProperty("max.daily.debit.amount.default"));
    }

    @Test
    void testGetAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(Account.builder()
                .id(1L)
                .customer(null)
                .number("123")
                .type(AccountType.SAVING)
                .maxDailyDebitAmount(100.0)
                .initialBalance(100.0)
                .balance(0.0)
                .status(StatusEnum.ENABLE)
                .build());
        accounts.add(Account.builder()
                .id(2L)
                .customer(null)
                .number("456")
                .type(AccountType.CHECKING)
                .maxDailyDebitAmount(200.0)
                .initialBalance(200.0)
                .balance(0.0)
                .status(StatusEnum.ENABLE)
                .build());

        when(accountRepository.findAll())
                .thenReturn(accounts);

        List<Account> result = accountService.getAccounts();

        Assertions.assertIterableEquals(result, accounts);
    }

    @Test
    public void testGetAccountById() {
        Account account = Account.builder()
                .id(1L)
                .customer(null)
                .number("123")
                .type(AccountType.SAVING)
                .maxDailyDebitAmount(100.0)
                .initialBalance(100.0)
                .balance(0.0)
                .status(StatusEnum.ENABLE)
                .build();

        when(accountRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(account));

        Account result = accountService.getAccountById(Mockito.anyLong());

        Assertions.assertEquals(result.getId(), account.getId());
    }

    @Test
    void testGetAccountByIdThrowsExceptionWhenAccountDoesNotExist() {
        when(accountRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountById(Mockito.anyLong());
        });
    }

    @Test
    void createAccountSavesAccountToRepositoryAndReturnsIt() {
        Customer customer = Customer.builder()
                .customerId(1L)
                .build();

        AccountDto accountDto = AccountDto.builder()
                .number("123")
                .customerId(customer.getCustomerId())
                .type(AccountType.SAVING)
                .initialBalance(100.0)
                .balance(100.0)
                .status(StatusEnum.ENABLE)
                .build();

        Account account = Account.builder()
                .id(1L)
                .customer(customer)
                .number("123")
                .type(AccountType.SAVING)
                .maxDailyDebitAmount(Double.valueOf(maxDailyDebitAmountDefault))
                .initialBalance(100.0)
                .balance(0.0)
                .status(StatusEnum.ENABLE)
                .build();

        when(customerService.getCustomerById(Mockito.anyLong()))
                .thenReturn(customer);

        when(accountRepository.saveAndFlush(Mockito.any()))
                .thenReturn(account);

        Account result = accountService.createAccount(accountDto);

        Assertions.assertEquals(result.getId(), account.getId());
    }
}
