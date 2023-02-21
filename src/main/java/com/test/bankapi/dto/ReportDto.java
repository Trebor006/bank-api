package com.test.bankapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private LocalDateTime date;
    private String customerName;
    private String accountNumber;
    private String accountType;
    private Double accountInitialBalance;
    private String accountStatus;
    private Double movementValue;
    private Double movementBalance;

}
