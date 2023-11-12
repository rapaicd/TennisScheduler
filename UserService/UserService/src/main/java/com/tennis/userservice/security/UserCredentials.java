package com.tennis.userservice.security;

import org.springframework.security.core.Authentication;

public class UserCredentials {
    public static String getUserEmail(Authentication authentication){
        return authentication.getName();
    }
    public static String getUserRole(Authentication authentication){
        return authentication.getAuthorities().iterator().next().getAuthority();
    }
}