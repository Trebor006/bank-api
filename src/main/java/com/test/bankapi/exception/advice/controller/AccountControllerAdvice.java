package com.test.bankapi.exception.advice.controller;

import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.ErrorApiResponseServiceInterface;
import com.test.bankapi.exception.AccountDebitMaxExcededException;
import com.test.bankapi.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AccountControllerAdvice {

    private final ErrorApiResponseServiceInterface errorApiResponseService;

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiResponse> handleAccountNotFoundException(AccountNotFoundException exception) {
        var responseError = errorApiResponseService.
                getObjectApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(responseError);
    }

    @ExceptionHandler(AccountDebitMaxExcededException.class)
    public ResponseEntity<ApiResponse> handleAccountDebitMaxExcededException(AccountDebitMaxExcededException exception) {
        var responseError = errorApiResponseService.
                getObjectApiResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseError);
    }
}
