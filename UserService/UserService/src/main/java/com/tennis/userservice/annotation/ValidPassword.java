package com.tennis.userservice.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConstraintsValidator.class)
public @interface ValidPassword {

    String message() default "Invalid password!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
