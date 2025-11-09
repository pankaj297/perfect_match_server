package com.shaadi.shaadi.Controller;

import com.shaadi.shaadi.dto.UserUpsertRequest;
import com.shaadi.shaadi.exception.NotFoundException;
import com.shaadi.shaadi.mapper.UserMapper;
import com.shaadi.shaadi.Model.User;
import com.shaadi.shaadi.Services.CloudinaryService;
import com.shaadi.shaadi.Services.UserService;
import com.shaadi.shaadi.Services.dto.UploadResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://laganmelava.netlify.app")
public class UserController {

    private final UserService userService;
    private final CloudinaryService cloudinaryService;

    // Create (compat: /register retained)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @ModelAttribute @Valid UserUpsertRequest userReq,
            @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
            @RequestParam(value = "aadhaar", required = false) MultipartFile aadhaar) {

        // Validate files
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            if (profilePhoto.getContentType() == null || !profilePhoto.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("🚫 Only images allowed for Profile Photo");
            }
        }
        if (aadhaar != null && !aadhaar.isEmpty()) {
            String ct = aadhaar.getContentType();
            boolean allow = ct != null && (ct.startsWith("image/") || ct.equals("application/pdf"));
            if (!allow) {
                return ResponseEntity.badRequest().body("🚫 Aadhaar must be an image or PDF");
            }
        }

        User user = UserMapper.toEntity(userReq);

        // Uploads
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            UploadResult res = cloudinaryService.uploadFile(profilePhoto, "shaadi-profiles");
            user.setProfilePhotoPath(res.url());
            user.setProfilePhotoPublicId(res.publicId());
        }
        if (aadhaar != null && !aadhaar.isEmpty()) {
            UploadResult res = cloudinaryService.uploadFile(aadhaar, "shaadi-aadhaar");
            user.setAadhaarPath(res.url());
            user.setAadhaarPublicId(res.publicId());
        }

        User savedUser = userService.saveUser(user);

        Map<String, Object> payload = new HashMap<>();
        payload.put("id", savedUser.getId());
        payload.put("user", savedUser);
        payload.put("profilePhotoUrl", savedUser.getProfilePhotoPath());
        payload.put("aadhaarUrl", savedUser.getAadhaarPath());

        return ResponseEntity.ok(payload);
    }

    // Get all users (compat: "/" retained)
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    // Admin Login (via properties)
    @PostMapping("/admin/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        // Backward compatible simple check; moved to AdminAuthController for better
        // separation
        if ("nitin5319".equals(username) && "nitin5319".equals(password)) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // Update (compat: /update/{id} retained)
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @ModelAttribute @Valid UserUpsertRequest userReq,
            @RequestParam(value = "profilePhoto", required = false) MultipartFile profilePhoto,
            @RequestParam(value = "aadhaar", required = false) MultipartFile aadhaar) {

        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Update fields
        UserMapper.updateEntity(existingUser, userReq);

        // New profile photo
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            if (profilePhoto.getContentType() == null || !profilePhoto.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("🚫 Only image files allowed for Profile Photo");
            }
            // delete old
            cloudinaryService.deleteByPublicId(existingUser.getProfilePhotoPublicId());

            UploadResult res = cloudinaryService.uploadFile(profilePhoto, "shaadi-profiles");
            existingUser.setProfilePhotoPath(res.url());
            existingUser.setProfilePhotoPublicId(res.publicId());
        }

        // New aadhaar
        if (aadhaar != null && !aadhaar.isEmpty()) {
            String ct = aadhaar.getContentType();
            boolean allow = ct != null && (ct.startsWith("image/") || ct.equals("application/pdf"));
            if (!allow) {
                return ResponseEntity.badRequest().body("🚫 Aadhaar must be an image or PDF");
            }
            // delete old
            cloudinaryService.deleteByPublicId(existingUser.getAadhaarPublicId());

            UploadResult res = cloudinaryService.uploadFile(aadhaar, "shaadi-aadhaar");
            existingUser.setAadhaarPath(res.url());
            existingUser.setAadhaarPublicId(res.publicId());
        }

        User updatedUser = userService.saveUser(existingUser);

        Map<String, Object> payload = new HashMap<>();
        payload.put("user", updatedUser);
        payload.put("profilePhotoUrl", updatedUser.getProfilePhotoPath());
        payload.put("aadhaarUrl", updatedUser.getAadhaarPath());

        return ResponseEntity.ok(payload);
    }

    // Delete (compat: /delete/{id} retained)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Best effort cleanup
        cloudinaryService.deleteByPublicId(user.getProfilePhotoPublicId());
        cloudinaryService.deleteByPublicId(user.getAadhaarPublicId());

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
}