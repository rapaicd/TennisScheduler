package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.repository.TimeslotRepository;
import com.tennis.timeslotservice.validator.annotations.OverlappingTimeslotsValidate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OverlappingTimeslotsValidator implements ConstraintValidator<OverlappingTimeslotsValidate, TimeslotDto> {
    private final TimeslotRepository timeslotRepository;

    @Override
    public void initialize(OverlappingTimeslotsValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeslotDto timeslotDto, ConstraintValidatorContext constraintValidatorContext) {
        if (timeslotRepository.checkOverlapping(timeslotDto.getDateStart(), timeslotDto.getDateEnd(), timeslotDto.getCourtId(), timeslotDto.getId()).size() > 0)
            return false;
        else
            return true;
    }
}
