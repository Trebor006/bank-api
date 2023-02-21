package com.test.bankapi.exception.advice.controller;

import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.ErrorApiResponseServiceInterface;
import com.test.bankapi.exception.CustomerCurrentPasswordException;
import com.test.bankapi.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class CustomerControllerAdvice {
    private final ErrorApiResponseServiceInterface errorApiResponseService;

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ApiResponse response = errorApiResponseService
                .getObjectApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(CustomerCurrentPasswordException.class)
    public ResponseEntity<ApiResponse> handleCustomerCurrentPasswordException(
            CustomerCurrentPasswordException exception
    ) {
        ApiResponse response = errorApiResponseService
                .getObjectApiResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
