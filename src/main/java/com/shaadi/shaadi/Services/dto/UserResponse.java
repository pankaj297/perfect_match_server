package com.shaadi.shaadi.Services.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private String id; // <-- String
    private String name;
    private String gender;
    private LocalDate dob;
    private String birthplace;
    private String kuldevat;
    private String gotra;
    private String height;
    private String bloodGroup;
    private String education;
    private String profession;
    private String fatherName;
    private String fatherProfession;
    private String motherName;
    private String motherProfession;
    private String siblings;
    private String mama;
    private String kaka;
    private String address;
    private String mobile;
    private String profilePhotoPath;
    private String aadhaarPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
