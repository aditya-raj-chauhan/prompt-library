package com.devotee.promptlibrary.service;

import com.devotee.promptlibrary.dto.ImageUploadResponse;
import com.devotee.promptlibrary.dto.UploadRequest;
import com.devotee.promptlibrary.dto.UploadResponse;
import com.devotee.promptlibrary.model.Upload;
import com.devotee.promptlibrary.model.User;
import com.devotee.promptlibrary.repositroy.UploadRepo;
import com.devotee.promptlibrary.repositroy.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UploadService {

    private final UploadRepo uploadRepo;
    private final UserRepo userRepo;
    private final CloudinaryService cloudinaryService;
    private final AuthenticationFacadeImpl authenticationFacade;

    public UploadResponse createUpload(
            UploadRequest uploadRequest,
            MultipartFile image
    ) throws IOException {

        User currentUser =
                authenticationFacade.getCurrentUser();

        ImageUploadResponse uploadedImage =
                cloudinaryService.uploadImage(image);

        LocalDateTime now =
                LocalDateTime.now();

        Upload upload = Upload.builder()
                .title(uploadRequest.getTitle())
                .prompt(uploadRequest.getPrompt())
                .description(uploadRequest.getDescription())
                .tags(uploadRequest.getTags())

                .imageUrl(
                        uploadedImage.getImageUrl()
                )

                .userId(
                        currentUser.getId()
                )

                .username(
                        currentUser.getUsername()
                )

                .profileImage(
                        currentUser.getProfileImage()
                )

                .likesCount(0)

                .copyCount(0)

                .createdAt(now)

                .updatedAt(now)

                .build();

        Upload savedUpload =
                uploadRepo.save(upload);

        return convertToResponse(
                savedUpload
        );
    }

    public List<UploadResponse> getAllUploads() {

        return uploadRepo.findAll()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Upload::getCreatedAt
                        ).reversed()
                )
                .map(this::convertToResponse)
                .toList();
    }

    public List<UploadResponse> getMyUploads() {

        User currentUser =
                authenticationFacade.getCurrentUser();

        return uploadRepo
                .findByUserId(
                        currentUser.getId()
                )
                .stream()
                .sorted(
                        Comparator.comparing(
                                Upload::getCreatedAt
                        ).reversed()
                )
                .map(this::convertToResponse)
                .toList();
    }

    public UploadResponse getUploadById(
            String id
    ) {

        Upload upload =
                uploadRepo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Upload not found"
                                )
                        );

        return convertToResponse(
                upload
        );
    }

    public void deleteUpload(
            String id
    ) {

        User currentUser =
                authenticationFacade.getCurrentUser();

        Upload upload =
                uploadRepo.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Upload not found"
                                )
                        );

        if (!upload.getUserId()
                .equals(currentUser.getId())) {

            throw new RuntimeException(
                    "You can only delete your own uploads"
            );
        }

        uploadRepo.delete(upload);
    }

    private UploadResponse convertToResponse(
            Upload upload
    ) {

        return UploadResponse.builder()
                .id(upload.getId())
                .title(upload.getTitle())
                .prompt(upload.getPrompt())
                .description(upload.getDescription())
                .imageUrl(upload.getImageUrl())
                .tags(upload.getTags())
                .userId(upload.getUserId())
                .username(upload.getUsername())
                .profileImage(upload.getProfileImage())
                .likesCount(upload.getLikesCount())
                .copyCount(upload.getCopyCount())
                .createdAt(upload.getCreatedAt())
                .updatedAt(upload.getUpdatedAt())
                .build();
    }
}