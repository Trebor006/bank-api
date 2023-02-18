package com.test.bankapi.controller;

import com.test.bankapi.component.mapper.MovementDtoMapperServiceInterface;
import com.test.bankapi.component.mapper.MovementLogicDtoMapperServiceInterface;
import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.SuccessApiResponseServiceInterface;
import com.test.bankapi.constants.RestRequestMappingConstants;
import com.test.bankapi.dto.entry.MovementDto;
import com.test.bankapi.service.MovementServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RestRequestMappingConstants.MOVEMENTS)
public class MovementController {

    private final MovementServiceInterface movementService;
    private final MovementDtoMapperServiceInterface movementDtoMapperService;
    private final SuccessApiResponseServiceInterface successApiResponseService;
    private final MovementLogicDtoMapperServiceInterface movementLogicDtoMapperService;

    @GetMapping
    public ResponseEntity<ApiResponse> getMovements() {
        var movements = movementService.getMovements();
        var movementDtos = movementDtoMapperService.mapFromEntity(movements);
        log.info("getMovements");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(movementDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMovementById(@PathVariable Long id) {
        var movement = movementService.getMovementById(id);
        var movementDto = movementDtoMapperService.mapFromEntity(movement);
        log.info("getMovementById");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(movementDto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createMovement(@RequestBody MovementDto requestMovementDto) {
        var movementLogicDto = movementLogicDtoMapperService.mapToBusinessDto(requestMovementDto);
        var movement = movementService.createMovement(movementLogicDto);
        var movementDto = movementDtoMapperService.mapFromEntity(movement);
        log.info("createMovement");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(movementDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMovement(@PathVariable Long id,
                                                      @RequestBody MovementDto requestMovementDto) {
        var movementLogicDto = movementLogicDtoMapperService.mapToBusinessDto(requestMovementDto);
        var movement = movementService.updateMovement(id, movementLogicDto);
        var movementDto = movementDtoMapperService.mapFromEntity(movement);
        log.info("updateMovement");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(movementDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateMovementValue(@PathVariable Long id,
                                                           @RequestBody MovementDto requestMovementDto) {
        var movementLogicDto = movementLogicDtoMapperService.mapToBusinessDto(requestMovementDto);
        var movement = movementService.updateMovement(id, movementLogicDto);
        var movementDto = movementDtoMapperService.mapFromEntity(movement);
        log.info("updateMovement");

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(movementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(Boolean.TRUE));
    }
}
