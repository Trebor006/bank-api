package com.test.bankapi.dto.business;

import com.test.bankapi.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementLogicDto {
    private Long id;
    private LocalDateTime date;
    private MovementType type;
    private Double value;
    private Double balance;
    private Long accountId;
}