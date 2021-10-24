package com.usermanagement.controller;

import com.usermanagement.dto.ViolationResponse;
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
    ViolationResponse onConstraintValidationException(MethodArgumentNotValidException e) {
        ViolationResponse error = new ViolationResponse();
        error.setViolations(new ArrayList<>());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }
        error.setTitle("Validasiya xətası.");
        return error;
    }
}
