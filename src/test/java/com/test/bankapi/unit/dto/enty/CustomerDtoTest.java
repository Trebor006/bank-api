package com.test.bankapi.unit.dto.enty;

import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerDtoTest {

    @Test
    public void testCustomerDto() {
        Long id = 1L;
        String name = "John Smith";
        String gender = "male";
        Integer age = 35;
        String identification = "123456789";
        String address = "123 Main Street";
        String phone = "555-555-1234";
        Long customerId = 2L;
        String password = "password123";
        StatusEnum status = StatusEnum.ENABLE;

        CustomerDto customerDto = CustomerDto.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .age(age)
                .identification(identification)
                .address(address)
                .phone(phone)
                .customerId(customerId)
                .password(password)
                .status(status)
                .build();

        assertEquals(id, customerDto.getId());
        assertEquals(name, customerDto.getName());
        assertEquals(gender, customerDto.getGender());
        assertEquals(age, customerDto.getAge());
        assertEquals(identification, customerDto.getIdentification());
        assertEquals(address, customerDto.getAddress());
        assertEquals(phone, customerDto.getPhone());
        assertEquals(customerId, customerDto.getCustomerId());
        assertEquals(password, customerDto.getPassword());
        assertEquals(status, customerDto.getStatus());
    }
}
