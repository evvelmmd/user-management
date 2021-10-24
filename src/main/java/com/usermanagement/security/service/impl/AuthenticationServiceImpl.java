package com.usermanagement.security.service.impl;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.dto.UserInfo;
import com.usermanagement.security.service.AuthenticationService;
import com.usermanagement.security.util.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    public String createAuthenticationToken(UserDTO request) {
        authenticate(request.getUsername(), request.getPassword());

        return tokenUtil.generateToken(request.getUsername());
    }

    @Override
    public UserInfo authorizeToken(String token) {
        tokenUtil.isTokenValid(token);

        return tokenUtil.getUserInfoFromToken(token);
    }


    public void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
