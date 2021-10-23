package com.usermanagement.security.service;

public interface AuthenticationService {
    void authenticate(String username, String password);
}
