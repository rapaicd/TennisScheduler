package com.tennis.authorizationserver.controller;


import com.tennis.authorizationserver.dto.RegisterUserDto;
import com.tennis.authorizationserver.dto.UpdatePasswordDto;
import com.tennis.authorizationserver.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/registration")
    public HttpStatus registerUser(@RequestBody RegisterUserDto registerUserDto){
        userDetailsService.register(registerUserDto.getEmail(),registerUserDto.getPassword());
        return HttpStatus.OK;
    }

    @PostMapping("/change-password")
    public void updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto, Authentication authentication) {
        userDetailsService.changePassword(updatePasswordDto.getOldPassword(),updatePasswordDto.getNewPassword(),authentication.getName());
    }

    @GetMapping("/admin")
    public String getAdminEmail() {
        return userDetailsService.getAdminEmail();
    }
}
