package com.test.bankapi.component.mapper;

import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomerDtoMapperService implements CustomerDtoMapperServiceInterface {

    @Override
    public List<CustomerDto> mapFromEntity(List<Customer> customers) {
        return customers.stream()
                .map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto mapFromEntity(Customer customer) {

        return CustomerDto.builder()
                .id(customer.getPerson().getId())
                .name(customer.getPerson().getName())
                .gender(customer.getPerson().getGender())
                .age(customer.getPerson().getAge())
                .identification(customer.getPerson().getIdentification())
                .address(customer.getPerson().getAddress())
                .phone(customer.getPerson().getPhone())
                .customerId(customer.getCustomerId())
                .password(customer.getPassword())
                .status(customer.getStatus())
                .build();
    }
}
