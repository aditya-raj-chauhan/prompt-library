package com.devotee.promptlibrary.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "uploads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Upload {

    @Id
    private String id;

    private String title;

    private String prompt;

    private String description;

    private String imageUrl;

    private List<String> tags = new ArrayList<>();

    private String userId;

    private String username;

    private String profileImage;

    private Integer likesCount;

    private Integer copyCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}