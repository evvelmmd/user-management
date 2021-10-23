package com.usermanagement.service.impl;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.entity.UserEntity;
import com.usermanagement.repository.UserRepository;
import com.usermanagement.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserDTO userDTO) {
        UserEntity userEntity = UserEntity
                .builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();

        userRepository.save(userEntity);
    }
}
