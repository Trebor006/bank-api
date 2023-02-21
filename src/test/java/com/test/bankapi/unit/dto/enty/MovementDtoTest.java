package com.test.bankapi.unit.dto.enty;

import com.test.bankapi.dto.entry.MovementDto;
import com.test.bankapi.enums.MovementType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovementDtoTest {

    @Test
    public void testMovementDto() {
        Long id = 1L;
        LocalDateTime date = LocalDateTime.now();
        MovementType type = MovementType.CREDIT;
        Double value = 100.0;
        Double balance = 200.0;
        Long accountId = 1L;

        MovementDto movementDto = MovementDto.builder()
                .id(id)
                .date(date)
                .type(type)
                .value(value)
                .balance(balance)
                .accountId(accountId)
                .build();

        assertEquals(id, movementDto.getId());
        assertEquals(date, movementDto.getDate());
        assertEquals(type, movementDto.getType());
        assertEquals(value, movementDto.getValue());
        assertEquals(balance, movementDto.getBalance());
        assertEquals(accountId, movementDto.getAccountId());
    }
}
