package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.business.MovementLogicDto;
import com.test.bankapi.dto.entry.MovementDto;
import com.test.bankapi.enums.MovementType;
import org.springframework.stereotype.Component;

@Component
public class MovementLogicDtoMapperService implements MovementLogicDtoMapperServiceInterface {

    @Override
    public MovementLogicDto mapToBusinessDto(MovementDto requestMovementDto) {
        return MovementLogicDto.builder()
                .id(requestMovementDto.getId())
                .date(requestMovementDto.getDate())
                .type(getType(requestMovementDto.getValue()))
                .value(requestMovementDto.getValue())
                .balance(requestMovementDto.getBalance())
                .accountId(requestMovementDto.getAccountId())
                .build();
    }

    private MovementType getType(Double value) {
        return value <= 0 ? MovementType.DEBIT : MovementType.CREDIT;
    }

}
