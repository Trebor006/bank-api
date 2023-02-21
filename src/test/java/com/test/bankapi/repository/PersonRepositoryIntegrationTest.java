package com.test.bankapi.repository;

import com.test.bankapi.entity.Person;
import com.test.bankapi.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSaveAndFindById() {
        Person person = new Person();
        person.setName("John Doe");
        person.setGender("Male");
        person.setAge(35);
        person.setIdentification("1234567890");
        person.setAddress("123 Main Street");
        person.setPhone("555-1234");
        person = personRepository.save(person);

        Optional<Person> result = personRepository
                .findById(person.getId());

        assertTrue(result.isPresent());
        assertEquals(result.get(), person);
    }
}
