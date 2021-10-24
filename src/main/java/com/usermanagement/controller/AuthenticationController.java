package com.usermanagement.controller;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.security.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authorize")
    public String authorize(@RequestBody UserDTO userDTO) {
        return authenticationService.createAuthenticationToken(userDTO);
    }
}
