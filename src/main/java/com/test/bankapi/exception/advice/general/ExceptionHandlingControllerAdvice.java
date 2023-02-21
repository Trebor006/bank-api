package com.test.bankapi.exception.advice.general;

import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.ErrorApiResponseServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlingControllerAdvice {

    private final ErrorApiResponseServiceInterface errorApiResponseService;

    @ExceptionHandler(value = {
            DataIntegrityViolationException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<ApiResponse> handleConstraintViolationException(Exception exception) {
        var responseError = errorApiResponseService.
                getObjectApiResponse(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseError);
    }
}
