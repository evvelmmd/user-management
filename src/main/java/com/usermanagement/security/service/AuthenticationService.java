package com.usermanagement.security.service;

import com.usermanagement.dto.UserDTO;
import com.usermanagement.dto.UserInfo;

public interface AuthenticationService {
    void authenticate(String username, String password);

    String createAuthenticationToken(UserDTO request);

    UserInfo authorizeToken(String token);
}
