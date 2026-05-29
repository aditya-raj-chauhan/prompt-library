package com.devotee.promptlibrary.service;

import com.devotee.promptlibrary.config.CustomUserDetails;
import com.devotee.promptlibrary.model.User;
import com.devotee.promptlibrary.repositroy.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email
                        ));

        return new CustomUserDetails(user);
    }
}