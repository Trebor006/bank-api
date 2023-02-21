package com.test.bankapi.controller;

import com.google.gson.Gson;
import com.test.bankapi.component.mapper.AccountDtoMapperServiceInterface;
import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.SuccessApiResponseServiceInterface;
import com.test.bankapi.constants.RestRequestMappingConstants;
import com.test.bankapi.dto.entry.AccountDto;
import com.test.bankapi.service.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RestRequestMappingConstants.ACCOUNTS)
public class AccountController {

    private final ObjectFactory<AccountServiceInterface> accountServiceFactory;
    private final AccountDtoMapperServiceInterface accountDtoMapperService;
    private final SuccessApiResponseServiceInterface successApiResponseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAccounts() {
        var accounts = accountServiceFactory.getObject().getAccounts();
        var accountDtos = accountDtoMapperService.mapFromEntity(accounts);
        log.debug("getAccounts Response : " + new Gson().toJson(accountDtos));

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(accountDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAccountById(@PathVariable Long id) {
        var account = accountServiceFactory.getObject().getAccountById(id);
        var accountDto = accountDtoMapperService.mapFromEntity(account);

        log.debug("getAccountById Response : " + new Gson().toJson(accountDto));
        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(accountDto));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createAccount(@RequestBody AccountDto requestAccountDto) {
        var account = accountServiceFactory.getObject().createAccount(requestAccountDto);
        var accountDto = accountDtoMapperService.mapFromEntity(account);

        log.debug("createAccount Response : " + new Gson().toJson(accountDto));

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(accountDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAccount(@PathVariable Long id,
                                                     @RequestBody AccountDto requestAccountDto) {
        var account = accountServiceFactory.getObject().updateAccount(id, requestAccountDto);
        var accountDto = accountDtoMapperService.mapFromEntity(account);

        log.debug("createAccount Response : " + new Gson().toJson(accountDto));

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(accountDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> partialUpdateAccount(@PathVariable Long id,
                                                            @RequestBody AccountDto requestAccountDto) {
        var account = accountServiceFactory.getObject().updateAccount(id, requestAccountDto);
        var accountDto = accountDtoMapperService.mapFromEntity(account);
        log.debug("partialUpdateAccount Response : " + new Gson().toJson(accountDto));


        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(accountDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable Long id) {
        log.debug("deleteAccount");
        accountServiceFactory.getObject().deleteAccount(id);

        return ResponseEntity.ok(successApiResponseService.createSuccessResponse(Boolean.TRUE));
    }
}
