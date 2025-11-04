package com.shaadi.shaadi.Services;

import com.shaadi.shaadi.Services.dto.UploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    UploadResult uploadFile(MultipartFile file, String folder);

    void deleteByPublicId(String publicId);
}