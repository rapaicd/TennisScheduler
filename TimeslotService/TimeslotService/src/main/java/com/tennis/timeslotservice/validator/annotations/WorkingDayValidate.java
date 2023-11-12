package com.tennis.timeslotservice.validator.annotations;

import com.tennis.timeslotservice.message.TimeslotMessage;
import com.tennis.timeslotservice.validator.WorkingDayValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WorkingDayValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkingDayValidate {
    String message() default TimeslotMessage.WORKING_TIME_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}