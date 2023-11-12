package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.repository.TimeslotRepository;
import com.tennis.timeslotservice.validator.annotations.IsReservedValidate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IsReservedValidator implements ConstraintValidator<IsReservedValidate, TimeslotDto> {
    private final TimeslotRepository timeslotRepository;

    @Override
    public void initialize(IsReservedValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeslotDto timeslotDto, ConstraintValidatorContext constraintValidatorContext) {
        return timeslotRepository.checkIfTimeslotIsAlreadyReserved(timeslotDto.getDateStart(), timeslotDto.getUserEmail(), timeslotDto.getId()) == null;
    }
}
