package com.tennis.userservice.service;

import com.tennis.userservice.dto.UserCredentialsDto;
import com.tennis.userservice.exception.BadRequestException;
import com.tennis.userservice.exception.UserNotAuthorizedException;
import com.tennis.userservice.exception.UserNotFoundException;
import com.tennis.userservice.model.Person;
import com.tennis.userservice.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final RestTemplate restTemplate;
    private final PasswordEncoder bCryptPasswordEncoder;
    private static String authorizationServerRegistrationUrl;
    private static String authorizationServerAdminUrl;

    @Value("${api.authorization-server-registration-url}")
    public void setAuthorizationServerRegistrationUrl(String authorizationServerRegistrationUrl){
        PersonService.authorizationServerRegistrationUrl = authorizationServerRegistrationUrl;
    }

    @Value("${api.authorization-server-admin-url}")
    public void setAuthorizationServerAdminUrl(String authorizationServerAdminUrl){
        PersonService.authorizationServerAdminUrl = authorizationServerAdminUrl;
    }

    public List<Person> getAllPersons(String token) {
        String adminEmail = getAdminEmail(token);
        return personRepository.findAll().stream()
                    .filter(person -> !person.getEmail().equals(adminEmail))
                    .collect(Collectors.toList());
    }

    private String getAdminEmail(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);

            HttpEntity request = new HttpEntity(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    authorizationServerAdminUrl,
                    HttpMethod.GET,
                    request,
                    String.class);
            return response.getBody();
        }catch (RuntimeException e){
            throw new RuntimeException("Connection refused!");
        }
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(()-> new UserNotFoundException("This id isn't valid"));
    }

    public Person savePerson(Person person, String password) throws BadRequestException {
        try {
            HttpStatus http = restTemplate.postForObject(
                    authorizationServerRegistrationUrl,
                    UserCredentialsDto
                            .builder()
                            .email(person.getEmail())
                            .password(bCryptPasswordEncoder.encode(password))
                            .build(),
                    HttpStatus.class);
            if(http == HttpStatus.OK)
                return personRepository.save(person);
        }catch (RuntimeException e){
            throw new RuntimeException("Connection refused!");
        }
        throw new BadRequestException("Bad request!");
    }

    public void deletePersonById(long id) {
        personRepository.findById(id).orElseThrow(()-> new UserNotFoundException("This id isn't valid"));
        personRepository.deleteById(id);
    }

    public Person updatePerson(long id, Person person, String role, String email) throws UserNotFoundException{
        if (role.equals("ROLE_user") && !Objects.equals(person.getEmail(), email))
            throw new UserNotAuthorizedException("You don't have permission.");

        Person existingPerson = personRepository.findById(id).orElseThrow(()-> new UserNotFoundException("This id isn't valid"));
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setBirthday(person.getBirthday());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setGender(person.getGender());
        return personRepository.save(existingPerson);
    }

    public Person findByEmail(String userEmail) {
        return personRepository.findByEmail(userEmail);
    }
}
