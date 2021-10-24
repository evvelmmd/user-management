package com.usermanagement.security.service;

import com.usermanagement.dto.UserDTO;

public interface AuthenticationService {
    void authenticate(String username, String password);

    String createAuthenticationToken(UserDTO request);
}
