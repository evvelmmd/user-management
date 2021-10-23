package com.usermanagement.controller;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.security.service.AuthenticationService;
import com.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDTO userDTO) {
        userService.signUp(userDTO);
    }

    @PostMapping(value = "/authorize")
    public String authorize(@RequestBody UserDTO userDTO) {
        authenticationService.authenticate(userDTO.getUsername(), userDTO.getPassword());
        return "Okey";
    }

}
