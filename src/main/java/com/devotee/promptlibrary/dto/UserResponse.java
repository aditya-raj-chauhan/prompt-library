package com.devotee.promptlibrary.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserResponse {

    private String id;

    private String username;

    private String fullName;

    private String email;

    private String role;

    private String bio;

    private String profileImage;

    private String bannerImage;

    private String website;

    private String location;

    private List<String> uploads;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}