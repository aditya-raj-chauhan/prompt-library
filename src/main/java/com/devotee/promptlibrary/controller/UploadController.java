package com.devotee.promptlibrary.controller;

import com.devotee.promptlibrary.dto.UploadRequest;
import com.devotee.promptlibrary.dto.UploadResponse;
import com.devotee.promptlibrary.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
@Slf4j
public class UploadController {

    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<UploadResponse> uploadPost(

            @ModelAttribute UploadRequest uploadRequest,

            @RequestParam("image")
            MultipartFile image

    ) throws IOException {

        log.info(
                "Upload request received for title: {}",
                uploadRequest.getTitle()
        );

        UploadResponse response =
                uploadService.createUpload(
                        uploadRequest,
                        image
                );

        log.info(
                "Upload created successfully with id: {}",
                response.getId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<UploadResponse>>
    getAllPostsByUploadDate() {

        log.info(
                "Fetching all uploads ordered by latest"
        );

        List<UploadResponse> uploads =
                uploadService.getAllUploads();

        log.info(
                "Total uploads fetched: {}",
                uploads.size()
        );

        return ResponseEntity.ok(uploads);
    }

    @GetMapping("/my-posts")
    public ResponseEntity<List<UploadResponse>>
    getAllUserPosts() {

        log.info(
                "Fetching current user uploads"
        );

        List<UploadResponse> uploads =
                uploadService.getMyUploads();

        return ResponseEntity.ok(uploads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UploadResponse>
    getPostById(
            @PathVariable String id
    ) {

        log.info(
                "Fetching upload by id: {}",
                id
        );

        UploadResponse upload =
                uploadService.getUploadById(id);

        return ResponseEntity.ok(upload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteUserPost(
            @PathVariable String id
    ) {

        log.info(
                "Delete upload request received for id: {}",
                id
        );

        uploadService.deleteUpload(id);

        log.info(
                "Upload deleted successfully: {}",
                id
        );

        return ResponseEntity.ok(
                "Upload deleted successfully"
        );
    }
}