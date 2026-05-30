package com.devotee.promptlibrary.repositroy;

import com.devotee.promptlibrary.model.Upload;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UploadRepo
        extends MongoRepository<Upload, String> {

    List<Upload> findByUserId(String userId);
}