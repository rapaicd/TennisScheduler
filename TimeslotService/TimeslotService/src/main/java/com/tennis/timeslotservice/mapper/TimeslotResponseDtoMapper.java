package com.tennis.timeslotservice.mapper;

import com.tennis.timeslotservice.dto.TimeslotResponseDto;
import com.tennis.timeslotservice.response.TimeslotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeslotResponseDtoMapper {
    @Mapping(source = "timeslot.startDate", target = "timeslot.dateStart")
    @Mapping(source = "timeslot.endDate", target = "timeslot.dateEnd")
    @Mapping(source = "timeslot.tennisCourt.id", target = "timeslot.courtId")
    TimeslotResponseDto toTimeslotResponseDto(TimeslotResponse timeslot);
}
