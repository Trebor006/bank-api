package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.business.MovementLogicDto;
import com.test.bankapi.dto.entry.MovementDto;

public interface MovementLogicDtoMapperServiceInterface {
    MovementLogicDto mapToBusinessDto(MovementDto requestMovementDto);
}
