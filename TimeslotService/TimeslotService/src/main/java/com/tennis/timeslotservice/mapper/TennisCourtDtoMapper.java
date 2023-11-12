package com.tennis.timeslotservice.mapper;

import com.tennis.timeslotservice.dto.TennisCourtDto;
import com.tennis.timeslotservice.model.TennisCourt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TennisCourtDtoMapper {
    @Mapping(source = "workingTime", target = "workingTimeDto")
    TennisCourtDto fromTennisCourtToTennisCourtDto (TennisCourt tennisCourt);
    @Mapping(source = "workingTimeDto", target = "workingTime")
    TennisCourt fromTennisCourtDtoToTennisCourt (TennisCourtDto tennisCourtDto);
}
