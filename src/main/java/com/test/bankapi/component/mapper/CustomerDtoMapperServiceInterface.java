package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.entity.Customer;

import java.util.List;

public interface CustomerDtoMapperServiceInterface {
    List<CustomerDto> mapFromEntity(List<Customer> customers);

    CustomerDto mapFromEntity(Customer customer);
}
