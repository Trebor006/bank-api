package com.test.bankapi.controller;

import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.ErrorApiResponseServiceInterface;
import com.test.bankapi.exception.MovementNoAllowedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class MovementControllerAdvice {

    private final ErrorApiResponseServiceInterface errorApiResponseService;

    @ExceptionHandler(MovementNoAllowedException.class)
    public ResponseEntity<ApiResponse> handleMovementNoAllowedException(MovementNoAllowedException exception) {
        var responseError = errorApiResponseService.
                getObjectApiResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseError);
    }

}
