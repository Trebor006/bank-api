package com.test.bankapi.dto.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MovementDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
    private MovementType type;
    private Double value;
    private Double balance;
    private Long accountId;
}