package com.test.bankapi.service;

import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.entity.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    List<Customer> getCustomers();

    Customer getCustomerById(Long id);

    Customer createCustomer(CustomerDto customerDto);

    Customer updateCustomer(Long id, CustomerDto customerDto);

    void deleteCustomer(Long id);
}
