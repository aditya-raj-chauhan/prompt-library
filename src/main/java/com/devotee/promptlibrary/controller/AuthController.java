package com.devotee.promptlibrary.controller;

import com.devotee.promptlibrary.dto.AuthRequest;
import com.devotee.promptlibrary.dto.AuthResponse;
import com.devotee.promptlibrary.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest authRequest
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        String token = jwtService.generateToken(
                authRequest.getEmail()
        );

        log.info("JWT generated for user {}", authRequest.getEmail());

        return AuthResponse.builder()
                .token(token)
                .build();
    }
}