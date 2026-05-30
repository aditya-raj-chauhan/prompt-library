package com.devotee.promptlibrary.service;

import com.devotee.promptlibrary.dto.ImageUploadResponse;
import com.devotee.promptlibrary.dto.UserRequest;
import com.devotee.promptlibrary.dto.UserResponse;
import com.devotee.promptlibrary.model.User;
import com.devotee.promptlibrary.repositroy.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepository;
    private final CloudinaryService cloudinaryService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest userRequest) {

        User user = convertToUser(userRequest);

        user.setPassword(
                passwordEncoder.encode(
                        userRequest.getPassword()
                )
        );

        User savedUser =
                userRepository.save(user);

        return convertToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public UserResponse getUserByEmail(
            String email
    ) {

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        return convertToResponse(user);
    }

    public void deleteUserByEmail(
            String email
    ) {

        User existingUser =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with this email"
                                )
                        );

        userRepository.delete(existingUser);
    }

    public UserResponse uploadProfileImage(
            String email,
            MultipartFile image
    ) throws IOException {

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        ImageUploadResponse uploadedImage =
                cloudinaryService.uploadImage(image);

        user.setProfileImage(
                uploadedImage.getImageUrl()
        );

        user.setUpdatedAt(
                LocalDateTime.now()
        );

        User savedUser =
                userRepository.save(user);

        return convertToResponse(savedUser);
    }

    public UserResponse uploadBannerImage(
            String email,
            MultipartFile image
    ) throws IOException {

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );

        ImageUploadResponse uploadedImage =
                cloudinaryService.uploadImage(image);

        user.setBannerImage(
                uploadedImage.getImageUrl()
        );

        user.setUpdatedAt(
                LocalDateTime.now()
        );

        User savedUser =
                userRepository.save(user);

        return convertToResponse(savedUser);
    }

    private User convertToUser(
            UserRequest userRequest
    ) {

        LocalDateTime now =
                LocalDateTime.now();

        return User.builder()
                .username(userRequest.getUsername())
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .bio(userRequest.getBio())
                .profileImage(userRequest.getProfileImage())
                .bannerImage(userRequest.getBannerImage())
                .website(userRequest.getWebsite())
                .location(userRequest.getLocation())
                .role("USER")
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private UserResponse convertToResponse(
            User user
    ) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .bio(user.getBio())
                .profileImage(user.getProfileImage())
                .bannerImage(user.getBannerImage())
                .website(user.getWebsite())
                .location(user.getLocation())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}