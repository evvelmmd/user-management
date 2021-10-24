package com.usermanagement.controller;

import com.usermanagement.dto.ViolationResponse;
import com.usermanagement.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ViolationResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ViolationResponse error = new ViolationResponse();
        error.setViolations(new ArrayList<>());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        error.setTitle("Validasiya xətası.");
        return error;
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ViolationResponse onAuthenticationException(AuthenticationException e) {
        ViolationResponse error = new ViolationResponse();
        error.setTitle("Giriş qadağandır");
        error.setDetail("Bu səhifəyə giriş üçün token əldə etməlisiniz");
        return error;
    }
}
