package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.request.AuthenticationRequest;
import net.javaguides.springboot.dto.request.RegisterRequest;
import net.javaguides.springboot.dto.response.AuthenticationResponse;

public interface AuthService {
    boolean userRegistration(RegisterRequest request);

    AuthenticationResponse userAuthentication(AuthenticationRequest request);
}
