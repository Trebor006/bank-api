package com.test.bankapi.dto.entry;

import com.test.bankapi.enums.AccountType;
import com.test.bankapi.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private Long customerId;
    private String number;
    private AccountType type;
    private Double initialBalance;
    private Double balance;
    private StatusEnum status;
}