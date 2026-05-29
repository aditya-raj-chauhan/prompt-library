package com.devotee.promptlibrary.controller;

import com.devotee.promptlibrary.dto.UserRequest;
import com.devotee.promptlibrary.dto.UserResponse;
import com.devotee.promptlibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserEntryController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody UserRequest userRequest
    ) {

        log.info("Register request received for email: {}", userRequest.getEmail());

        UserResponse response = userService.register(userRequest);

        log.info("User registered successfully with username: {}", response.getUsername());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {

        log.info("Fetching all users");

        List<UserResponse> users = userService.getAllUsers();

        log.info("Total users fetched: {}", users.size());

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(
            @PathVariable String email
    ) {

        log.info("Fetching user by email: {}", email);

        UserResponse user = userService.getUserByEmail(email);

        log.info("User fetched successfully: {}", user.getUsername());

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/delete/{email}")
    public ResponseEntity<?> deleteUser(
            @PathVariable String email
    ) {

        log.info("Delete request received for user email: {}", email);

        userService.deleteUserByEmail(email);

        log.info("User deleted successfully with email: {}", email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User deleted successfully");
    }

    @PostMapping("/profile-image")
    public ResponseEntity<?> uploadProfileImage(

            @RequestParam String email,

            @RequestParam MultipartFile image

    ) throws IOException {

        log.info(
                "Profile image upload request received for email: {}",
                email
        );

        UserResponse response =
                userService.uploadProfileImage(
                        email,
                        image
                );

        log.info(
                "Profile image uploaded successfully for user: {}",
                response.getUsername()
        );

        return ResponseEntity.ok(response);
    }


    @PostMapping("/banner-image")
    public ResponseEntity<?> uploadBannerImage(

            @RequestParam String email,

            @RequestParam MultipartFile image

    ) throws IOException {

        log.info(
                "Banner image upload request received for email: {}",
                email
        );

        UserResponse response =
                userService.uploadBannerImage(
                        email,
                        image
                );

        log.info(
                "Banner image uploaded successfully for user: {}",
                response.getUsername()
        );

        return ResponseEntity.ok(response);
    }
}