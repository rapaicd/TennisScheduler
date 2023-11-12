package com.tennis.timeslotservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "User isn't authorized!")
public class UserNotAuthorizedException extends RuntimeException{

    public UserNotAuthorizedException(String message){
        super(message);
    }
}
