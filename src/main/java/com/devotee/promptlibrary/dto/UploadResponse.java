package com.devotee.promptlibrary.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UploadResponse {

    private String id;

    private String title;

    private String prompt;

    private String description;

    private String imageUrl;

    private List<String> tags;

    private String userId;

    private String username;

    private String profileImage;

    private Integer likesCount;

    private Integer copyCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}