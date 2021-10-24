package com.usermanagement.controller;

import com.usermanagement.dto.UserSignInDTO;
import com.usermanagement.security.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authorize")
    public String authorize(@Valid @RequestBody UserSignInDTO userSignInDTO) {
        return authenticationService.createAuthenticationToken(userSignInDTO);
    }
}
