package com.shaadi.shaadi.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Value
@Builder
public class UserResponse {
    Long id;
    String name;
    String gender;
    LocalDate dob;
    String birthplace;
    String kuldevat;
    String gotra;
    String height;
    String bloodGroup;
    String education;
    String profession;
    String fatherName;
    String fatherProfession;
    String motherName;
    String motherProfession;
    String siblings;
    String mama;
    String kaka;
    String address;
    String mobile;
    String profilePhotoPath;
    String aadhaarPath;
    OffsetDateTime createdAt;
    OffsetDateTime updatedAt;
}