package com.tennis.timeslotservice.mapper;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.model.Timeslot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeslotDtoMapper {
    @Mapping(source = "startDate", target = "dateStart")
    @Mapping(source = "endDate", target = "dateEnd")
//    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "tennisCourt.id", target = "courtId")
    TimeslotDto fromTimeslotToTimeslotDto (Timeslot timeslot);
    @Mapping(source = "dateStart", target = "startDate")
    @Mapping(source = "dateEnd", target = "endDate")
//    @Mapping(source = "personId", target = "person.id")
    @Mapping(source = "courtId", target = "tennisCourt.id")
    Timeslot fromTimeslotDtoToTimeslot(TimeslotDto timeslotNewDto);
}
