package com.devotee.promptlibrary.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String fullName;

    @Indexed(unique = true)
    private String email;

    private String password;

    private String role;

    private String bio;

    private String profileImage;

    private String bannerImage;

    private String website;

    private String location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}