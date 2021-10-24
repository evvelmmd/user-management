package com.usermanagement.validation;

import com.usermanagement.entity.UserEntity;
import com.usermanagement.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements
        ConstraintValidator<UsernameConstraint, String> {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(value);
        return userEntity == null;
    }
}
