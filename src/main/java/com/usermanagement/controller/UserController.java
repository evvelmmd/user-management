package com.usermanagement.controller;

import com.usermanagement.dto.UserDTO;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserDTO userDTO) {
        userService.signUp(userDTO);
    }


}
