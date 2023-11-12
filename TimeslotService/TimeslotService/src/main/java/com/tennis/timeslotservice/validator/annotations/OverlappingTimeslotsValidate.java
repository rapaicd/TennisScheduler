package com.tennis.timeslotservice.validator.annotations;

import com.tennis.timeslotservice.message.TimeslotMessage;
import com.tennis.timeslotservice.validator.OverlappingTimeslotsValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OverlappingTimeslotsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OverlappingTimeslotsValidate {
    String message() default TimeslotMessage.OVERLAPPING_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}