package com.test.bankapi.component.response;

public interface SuccessApiResponseServiceInterface {
    <T> ApiResponse createSuccessResponse(T data);
}
