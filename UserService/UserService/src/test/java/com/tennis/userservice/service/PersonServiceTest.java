package com.tennis.userservice.service;

import com.tennis.userservice.dto.UserCredentialsDto;
import com.tennis.userservice.model.Person;
import com.tennis.userservice.model.enume.Gender;
import com.tennis.userservice.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonService personService;
    @Mock
    RestTemplate restTemplate;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Person person = Person.builder()
            .id(0)
            .firstName("Zoran")
            .lastName("Zoranovic")
            .email("zoran@gmail.com")
            .birthday(new Date(2000, Calendar.SEPTEMBER,20))
            .gender(Gender.MALE)
            .phoneNumber("0621234567")
            .build();

    @Disabled
    @Test
    void canGetAllPersons() {
        when(personRepository.findAll()).thenReturn(List.of(person));
        when(restTemplate.exchange(
                anyString(),//ne gleda ga kao string
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(String.class)
        )).thenReturn(new ResponseEntity<>("asd",HttpStatus.OK));
        assertEquals(List.of(person),personService.getAllPersons(""));
    }

    @Disabled
    @Test
    void canSavePerson() {
        when(restTemplate.postForObject(
                anyString(),//ne gleda ga kao string
                any(UserCredentialsDto.class),
                eq(HttpStatus.class)
        )).thenReturn(HttpStatus.OK);
        when(personRepository.save(any(Person.class))).thenReturn(person);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("");
        assertEquals(person,personService.savePerson(person,""));
    }

    @Test
    void canGetPersonById() {
        Optional<Person> getPerson = Optional.of(person);
        when(personRepository.findById(anyLong())).thenReturn(getPerson);

        assertEquals(getPerson,Optional.of(personService.findById(0L)));
    }

    @Test
    void canDeletePersonById() {
        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));
        personService.deletePersonById(person.getId());

        verify(personRepository).deleteById(person.getId());
    }

    @Test
    void canUpdatePerson() {

        Person newPerson = Person.builder()
                .firstName("Milana")
                .lastName("Milic")
                .email(person.getEmail())
                .gender(Gender.FEMALE)
                .phoneNumber("12123123")
                .build();

        when(personRepository.findById(any(Long.class))).thenReturn(Optional.of(person));
        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.personRepository).save(isA(Person.class));

        Person updatedPerson = personService.updatePerson(0L,newPerson,"ROLE_user","zoran@gmail.com");
        assertEquals("Milana",updatedPerson.getFirstName());
        assertEquals("Milic",updatedPerson.getLastName());
        assertEquals(Gender.FEMALE,updatedPerson.getGender());
        assertEquals("12123123",updatedPerson.getPhoneNumber());
    }
}
