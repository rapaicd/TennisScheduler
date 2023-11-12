package com.tennis.userservice.mapper;

import com.tennis.userservice.dto.PersonDto;
import com.tennis.userservice.model.Person;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-12T09:51:21+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class PersonDtoMapperImpl implements PersonDtoMapper {

    @Override
    public PersonDto fromPersonToPersonDto(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonDto personDto = new PersonDto();

        personDto.setId( person.getId() );
        personDto.setFirstName( person.getFirstName() );
        personDto.setLastName( person.getLastName() );
        personDto.setEmail( person.getEmail() );
        personDto.setGender( person.getGender() );
        personDto.setPhoneNumber( person.getPhoneNumber() );
        personDto.setBirthday( person.getBirthday() );

        return personDto;
    }

    @Override
    public Person fromPersonDtoToPerson(PersonDto personDto) {
        if ( personDto == null ) {
            return null;
        }

        Person.PersonBuilder person = Person.builder();

        person.id( personDto.getId() );
        person.firstName( personDto.getFirstName() );
        person.lastName( personDto.getLastName() );
        person.email( personDto.getEmail() );
        person.gender( personDto.getGender() );
        person.phoneNumber( personDto.getPhoneNumber() );
        person.birthday( personDto.getBirthday() );

        return person.build();
    }
}
