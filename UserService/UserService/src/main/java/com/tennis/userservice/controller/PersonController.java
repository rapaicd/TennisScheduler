package com.tennis.userservice.controller;

import com.tennis.userservice.dto.PersonDto;
import com.tennis.userservice.mapper.PersonDtoMapper;
import com.tennis.userservice.security.BearerTokenHeader;
import com.tennis.userservice.security.UserCredentials;
import com.tennis.userservice.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonDtoMapper personDtoMapper;

    @GetMapping("/")
    public List<PersonDto>getAllPersons(){
        return personService.getAllPersons(BearerTokenHeader.get()).stream()
                .map(person ->personDtoMapper.fromPersonToPersonDto(person))
                .collect(Collectors.toList());
    }

    @PostMapping("/registration")
    public PersonDto savePerson(@RequestBody @Valid PersonDto personNew) {
        return personDtoMapper.fromPersonToPersonDto(personService.savePerson(personDtoMapper.fromPersonDtoToPerson(personNew), personNew.getPassword()));
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable long id) {
        personService.deletePersonById(id);
    }

    @GetMapping("/logged-user")
    public PersonDto getLoggedUser(Authentication authentication){
        return personDtoMapper.fromPersonToPersonDto(personService.findByEmail(UserCredentials.getUserEmail(authentication)));
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable long id){
        return personDtoMapper.fromPersonToPersonDto(personService.findById(id));
    }

    @PutMapping("/{id}")
    public PersonDto updatePerson(@PathVariable long id, @RequestBody PersonDto personDto, Authentication authentication){
        return personDtoMapper.fromPersonToPersonDto(personService.updatePerson(id, personDtoMapper.fromPersonDtoToPerson(personDto), UserCredentials.getUserRole(authentication),UserCredentials.getUserEmail(authentication)));
    }
}
