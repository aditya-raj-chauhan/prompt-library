package com.devotee.promptlibrary.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ImageUploadResponse {

    private String imageUrl;

    private String publicId;
}