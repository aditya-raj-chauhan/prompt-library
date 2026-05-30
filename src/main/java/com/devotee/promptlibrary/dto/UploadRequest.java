package com.devotee.promptlibrary.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadRequest {

    private String title;

    private String prompt;

    private String description;

    private List<String> tags;
}