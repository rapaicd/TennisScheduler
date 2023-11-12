package com.tennis.userservice.repository;

import com.tennis.userservice.model.Person;
import com.tennis.userservice.model.enume.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;
    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void findById() {

        Person person = Person.builder()
                .firstName("Zoran")
                .lastName("Zoranovic")
                .email("zoran@gmail.com")
                .birthday(new Date(2000, Calendar.MARCH,12))
                .gender(Gender.MALE)
                .phoneNumber("0621234567")
                .build();

        Person savedPerson = personRepository.save(person);

        Person expected = personRepository.findById(savedPerson.getId()).get();

        assertThat(expected).isEqualTo(savedPerson);
    }
}