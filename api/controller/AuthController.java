package com.example.api.controller;

import com.example.api.controller.dto.LoginRequest;
import com.example.api.controller.dto.LoginResponse;
import com.example.api.jwt.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.userName(), request.password())
        );

        // Exercise 2 audit: INFO on successful login
        log.info("LOGIN success user={}", auth.getName());

        String token = jwtService.generateToken(auth.getName(), auth.getAuthorities());
        return new LoginResponse(token);
    }
}
