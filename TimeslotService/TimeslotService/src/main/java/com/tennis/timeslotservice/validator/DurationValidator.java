package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.validator.annotations.DurationValidate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class DurationValidator  implements ConstraintValidator<DurationValidate, TimeslotDto> {

    @Override
    public void initialize(DurationValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeslotDto timeslotDto, ConstraintValidatorContext constraintValidatorContext) {

        Minutes minutes = Minutes.minutesBetween(new DateTime(timeslotDto.getDateStart()), new DateTime(timeslotDto.getDateEnd()));
        if(minutes.isGreaterThan(Minutes.minutes(120)) || minutes.isLessThan(Minutes.minutes(30)))
            return false;

        return true;
    }
}
