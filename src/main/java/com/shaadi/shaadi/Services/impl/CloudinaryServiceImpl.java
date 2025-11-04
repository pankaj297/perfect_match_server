package com.shaadi.shaadi.Services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.shaadi.shaadi.Services.CloudinaryService;
import com.shaadi.shaadi.Services.dto.UploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public UploadResult uploadFile(MultipartFile file, String folder) {
        try {
            if (file == null || file.isEmpty())
                return null;

            Map<?, ?> res = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "use_filename", true,
                            "unique_filename", true,
                            "resource_type", "auto" // supports images, pdf, etc.
                    ));
            String url = res.get("secure_url").toString();
            String publicId = res.get("public_id").toString();
            String resourceType = res.get("resource_type").toString();
            return new UploadResult(url, publicId, resourceType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to Cloudinary", e);
        }
    }

    @Override
    public void deleteByPublicId(String publicId) {
        try {
            if (publicId == null || publicId.isBlank())
                return;
            // Try image
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true, "resource_type", "image"));
            // Try raw (pdf etc.)
            cloudinary.uploader().destroy(publicId, ObjectUtils.asMap("invalidate", true, "resource_type", "raw"));
        } catch (Exception ignored) {
        }
    }
}