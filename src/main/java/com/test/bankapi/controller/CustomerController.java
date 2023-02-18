package com.test.bankapi.controller;

import com.test.bankapi.component.mapper.CustomerDtoMapperServiceInterface;
import com.test.bankapi.component.response.ApiResponse;
import com.test.bankapi.component.response.SuccessApiResponseServiceInterface;
import com.test.bankapi.constants.RestRequestMappingConstants;
import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.entity.Customer;
import com.test.bankapi.service.CustomerServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(RestRequestMappingConstants.CUSTOMERS)
public class CustomerController {

    private final CustomerServiceInterface customerService;
    private final CustomerDtoMapperServiceInterface customerDtoMapperService;
    private final SuccessApiResponseServiceInterface successApiResponseService;

    @GetMapping
    public ResponseEntity<ApiResponse> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDto> customerDtos = customerDtoMapperService.mapFromEntity(customers);

        log.info("getCustomers");

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(customerDtos)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCustomerById(@PathVariable Long id) {
        var customer = customerService.getCustomerById(id);
        var customerDto = customerDtoMapperService.mapFromEntity(customer);

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(customerDto)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDto requestCustomerDto) {
        var customer = customerService.createCustomer(requestCustomerDto);
        var customerDto = customerDtoMapperService.mapFromEntity(customer);

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(customerDto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto requestCustomerDto) {
        var customer = customerService.updateCustomer(id, requestCustomerDto);
        var customerDto = customerDtoMapperService.mapFromEntity(customer);

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(customerDto)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> partialUpdateCustomer(@PathVariable Long id, @RequestBody CustomerDto requestCustomerDto) {
        var customer = customerService.updateCustomer(id, requestCustomerDto);
        var customerDto = customerDtoMapperService.mapFromEntity(customer);

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(customerDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                successApiResponseService.createSuccessResponse(Boolean.TRUE)
        );
    }
}
