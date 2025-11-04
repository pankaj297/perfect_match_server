package com.shaadi.shaadi.Controller;


import com.shaadi.shaadi.Services.CloudinaryService;
import com.shaadi.shaadi.Services.dto.UploadResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder", defaultValue = "shaadi-profiles") String folder) {
        if (file == null || file.isEmpty() || file.getContentType() == null
                || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body(Map.of("error", "🚫 Only image uploads are allowed"));
        }
        UploadResult res = cloudinaryService.uploadFile(file, folder);
        return ResponseEntity.ok(Map.of("url", res.url(), "publicId", res.publicId()));
    }
}