package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.MovementDto;
import com.test.bankapi.entity.Movement;

import java.util.List;

public interface MovementDtoMapperServiceInterface {
    List<MovementDto> mapFromEntity(List<Movement> movements);

    MovementDto mapFromEntity(Movement movement);
}
