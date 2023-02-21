package com.test.bankapi.service;

import com.test.bankapi.component.messages.MessageServiceInterface;
import com.test.bankapi.dto.entry.CustomerDto;
import com.test.bankapi.entity.Customer;
import com.test.bankapi.entity.Person;
import com.test.bankapi.exception.CustomerCurrentPasswordException;
import com.test.bankapi.exception.CustomerNotFoundException;
import com.test.bankapi.repository.CustomerRepository;
import com.test.bankapi.repository.PersonRepository;
import com.test.bankapi.util.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerService implements CustomerServiceInterface {

    private final MessageServiceInterface messageService;
    private final CustomerRepository customerRepository;

    //todo Mover la logica a personService
    private final PersonRepository personRepository;

    @Override
    public List<Customer> getCustomers() {
        var customerList = customerRepository.findAll();

        return customerList;
    }

    @Override
    public Customer getCustomerById(Long id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customer;
    }

    @Override
    @Transactional
    public Customer createCustomer(CustomerDto customerDto) {
        var passwordEncrypted = PasswordEncryption.encrypt(customerDto.getPassword());

        var person = Person.builder()
                .name(customerDto.getName())
                .gender(customerDto.getGender())
                .age(customerDto.getAge())
                .identification(customerDto.getIdentification())
                .address(customerDto.getAddress())
                .phone(customerDto.getPhone())
                .build();

        personRepository.saveAndFlush(person);

        var customer = Customer.builder()
                .person(person)
                .password(passwordEncrypted)
                .status(customerDto.getStatus())
                .build();
        customerRepository.saveAndFlush(customer);

        return customer;
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        var currentCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        var passwordEncrypted = PasswordEncryption.encrypt(customerDto.getPassword());

        currentCustomer.getPerson().setName(customerDto.getName());
        currentCustomer.getPerson().setGender(customerDto.getGender());
        currentCustomer.getPerson().setAge(customerDto.getAge());
        currentCustomer.getPerson().setIdentification(customerDto.getIdentification());
        currentCustomer.getPerson().setAddress(customerDto.getAddress());
        currentCustomer.getPerson().setPhone(customerDto.getPhone());

        personRepository.saveAndFlush(currentCustomer.getPerson());


        currentCustomer.setPassword(passwordEncrypted);
        currentCustomer.setStatus(customerDto.getStatus());
        customerRepository.saveAndFlush(currentCustomer);

        return currentCustomer;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        //todo Cambiar el eliminar fisico por un cambio de estado

        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.deleteById(id);
        personRepository.deleteById(customer.getPerson().getId());
    }

    @Override
    public void updatePassword(Long id, String currentPassword, String newPassword) {
        Customer customer = getCustomerById(id);
        var currentPasswordEncrypted = PasswordEncryption.encrypt(currentPassword);

        if (!currentPasswordEncrypted.equals(customer.getPassword())) {
            throw new CustomerCurrentPasswordException(messageService.getMessage("current.password.exception"));
        }

        var newPasswordEncrypted = PasswordEncryption.encrypt(newPassword);
        customer.setPassword(newPasswordEncrypted);
        customerRepository.saveAndFlush(customer);
    }
}
