package com.devotee.promptlibrary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.devotee.promptlibrary.dto.ImageUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class CloudinaryService {

    private final Cloudinary cloudinary;

    public ImageUploadResponse uploadImage(
            MultipartFile file
    ) throws IOException {

        Map<?, ?> uploadResult =
                cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.emptyMap()
                );

        return ImageUploadResponse.builder()
                .imageUrl(
                        uploadResult.get("secure_url").toString()
                )
                .publicId(
                        uploadResult.get("public_id").toString()
                )
                .build();
    }
}