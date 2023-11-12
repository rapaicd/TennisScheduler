package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.validator.annotations.SameDateValidate;
import org.apache.commons.lang.time.DateUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SameDateValidator implements ConstraintValidator<SameDateValidate, TimeslotDto> {

    @Override
    public void initialize(SameDateValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeslotDto timeslotDto, ConstraintValidatorContext constraintValidatorContext) {
        if(timeslotDto.getDateStart().after(timeslotDto.getDateEnd()) || !DateUtils.isSameDay(timeslotDto.getDateStart(), timeslotDto.getDateEnd()))
            return false;
        return true;
    }
}
