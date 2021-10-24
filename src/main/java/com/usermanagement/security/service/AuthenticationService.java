package com.usermanagement.security.service;

import com.usermanagement.dto.UserInfo;
import com.usermanagement.dto.UserSignInDTO;

public interface AuthenticationService {
    void authenticate(String username, String password);

    String createAuthenticationToken(UserSignInDTO request);

    UserInfo authorizeToken(String token);
}
