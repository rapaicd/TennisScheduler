package com.tennis.timeslotservice.validator.annotations;

import com.tennis.timeslotservice.message.TimeslotMessage;
import com.tennis.timeslotservice.validator.DurationValidator;
import javax.validation.Constraint;
import javax.validation.Payload;


import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DurationValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DurationValidate {
    String message() default TimeslotMessage.DURATION_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}