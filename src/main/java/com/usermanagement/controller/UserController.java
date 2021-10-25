package com.usermanagement.controller;

import com.usermanagement.dto.UserInfo;
import com.usermanagement.dto.UserSignUpDTO;
import com.usermanagement.security.service.AuthenticationService;
import com.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public void signUp(@RequestBody @Valid UserSignUpDTO userSignUpDTO) {
        userService.signUp(userSignUpDTO);
    }


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo getUserInfo(@RequestHeader("X-Auth-Token") String token) {
        return authenticationService.authorizeToken(token);
    }

}
