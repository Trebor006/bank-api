package com.test.bankapi.unit.dto.enty;


import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.enums.AccountType;
import com.test.bankapi.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountDtoTest {

    @Test
    void testAccountDto() {
        AccountDto accountDto = AccountDto.builder()
                .id(1L)
                .customerId(2L)
                .number("12345")
                .type(AccountType.CHECKING)
                .initialBalance(1000.0)
                .balance(1000.0)
                .status(StatusEnum.ENABLE)
                .build();

        assertEquals(1L, accountDto.getId());
        assertEquals(2L, accountDto.getCustomerId());
        assertEquals("12345", accountDto.getNumber());
        assertEquals(AccountType.CHECKING, accountDto.getType());
        assertEquals(1000.0, accountDto.getInitialBalance());
        assertEquals(1000.0, accountDto.getBalance());
        assertEquals(StatusEnum.ENABLE, accountDto.getStatus());
    }
}