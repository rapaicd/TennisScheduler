package com.tennis.timeslotservice.validator;

import com.tennis.timeslotservice.dto.TimeslotDto;
import com.tennis.timeslotservice.model.TennisCourt;
import com.tennis.timeslotservice.repository.TennisCourtRepository;
import com.tennis.timeslotservice.validator.annotations.WorkingDayValidate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.joda.time.DateTimeConstants;

@AllArgsConstructor

public class WorkingDayValidator implements ConstraintValidator<WorkingDayValidate, TimeslotDto> {

    private final TennisCourtRepository tennisCourtRepository;

    @Override
    public void initialize(WorkingDayValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TimeslotDto timeslotDto, ConstraintValidatorContext constraintValidatorContext) {

        TennisCourt tennisCourt = tennisCourtRepository.findById(timeslotDto.getCourtId()).get();
        if(timeslotDto.getDateStart().getDay() == DateTimeConstants.SATURDAY
                || timeslotDto.getDateStart().getDay() == DateTimeConstants.SUNDAY){

            if(timeslotDto.getDateStart().getHours() < tennisCourt.getWorkingTime().getStartWorkingTimeWeekend().getHours()
                    || timeslotDto.getDateEnd().getHours() >= tennisCourt.getWorkingTime().getEndWorkingTimeWeekend().getHours()) {
                if(timeslotDto.getDateStart().getMinutes() < tennisCourt.getWorkingTime().getStartWorkingTimeWeekend().getMinutes()
                        || timeslotDto.getDateEnd().getMinutes() >= tennisCourt.getWorkingTime().getEndWorkingTimeWeekend().getMinutes())
                    return false;
            }
        }
        else{
            if(timeslotDto.getDateStart().getHours() < tennisCourt.getWorkingTime().getStartWorkingTimeWeekDay().getHours()
                    ||  timeslotDto.getDateEnd().getHours() >= tennisCourt.getWorkingTime().getEndWorkingTimeWeekDay().getHours())
                if(timeslotDto.getDateStart().getMinutes() < tennisCourt.getWorkingTime().getStartWorkingTimeWeekDay().getMinutes()
                        ||  timeslotDto.getDateEnd().getMinutes() >= tennisCourt.getWorkingTime().getEndWorkingTimeWeekDay().getMinutes())
                    return false;
        }
        return true;
    }
}
