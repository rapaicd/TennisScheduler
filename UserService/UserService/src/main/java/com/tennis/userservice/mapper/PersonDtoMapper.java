package com.tennis.userservice.mapper;

import com.tennis.userservice.dto.PersonDto;
import com.tennis.userservice.model.Person;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PersonDtoMapper {
    PersonDto fromPersonToPersonDto (Person person);
    Person fromPersonDtoToPerson (PersonDto personDto);
}
