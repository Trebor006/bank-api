package com.test.bankapi.service;

import com.test.bankapi.dto.business.MovementLogicDto;
import com.test.bankapi.entity.Movement;

import java.util.List;

public interface MovementServiceInterface {
    List<Movement> getMovements();

    Movement getMovementById(Long id);

    Movement createMovement(MovementLogicDto movementDto);

    Movement updateMovement(Long id, MovementLogicDto movementDto);

    void deleteMovement(Long id);
}
