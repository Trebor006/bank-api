package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.MovementDto;
import com.test.bankapi.entity.Movement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MovementDtoMapperService implements MovementDtoMapperServiceInterface {

    @Override
    public List<MovementDto> mapFromEntity(List<Movement> movements) {
        return movements.stream()
                .map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovementDto mapFromEntity(Movement movement) {
        return MovementDto.builder()
                .id(movement.getId())
                .date(movement.getDate())
                .type(movement.getType())
                .value(movement.getValue())
                .balance(movement.getBalance())
                .accountId(movement.getAccount().getId())
                .build();
    }
}
