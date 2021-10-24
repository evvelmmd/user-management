package com.usermanagement.service.impl;

import com.usermanagement.dto.UserSignUpDTO;
import com.usermanagement.entity.UserEntity;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserSignUpDTO userSignUpDTO) {
        String password = new BCryptPasswordEncoder().encode(userSignUpDTO.getPassword());
        UserEntity userEntity = UserEntity
                .builder()
                .username(userSignUpDTO.getUsername())
                .password(password)
                .build();

        userRepository.save(userEntity);
    }
}
