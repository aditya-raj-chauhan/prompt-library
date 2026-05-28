package com.devotee.promptlibrary.dto;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserRequest {

    private String username;

    private String fullName;

    private String email;

    private String password;

    private String bio;

    private String profileImage;

    private String bannerImage;

    private String website;

    private String location;
}