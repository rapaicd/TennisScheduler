package com.tennis.userservice.dto;

import com.tennis.userservice.annotation.ValidPassword;
import com.tennis.userservice.model.enume.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private long id;
    @NotEmpty(message = "First name cannot be empty")
    @Length(min = 3, message = "The first name must have at least 2 characters")
    @NonNull
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty")
    @Length(min = 3, message = "The last name must have at least 2 characters")
    @NonNull
    private String lastName;
    @Email(message = "Email is not valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull
    private String email;
    private Gender gender;
    private String phoneNumber;
    private Date birthday;
    @ValidPassword
    private String password;
}
