package com.tennis.userservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCredentialsDto {

    private String email;
    private String password;
}
