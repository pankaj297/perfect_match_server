package com.shaadi.shaadi.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private Long id; // Mongo ObjectId as hex string

    @Size(max = 120)
    private String name;

    @Size(max = 20)
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Size(max = 120)
    private String birthplace;

    @Size(max = 120)
    private String kuldevat;

    @Size(max = 120)
    private String gotra;

    @Size(max = 20)
    private String height;

    @Size(max = 10)
    private String bloodGroup;

    @Size(max = 120)
    private String education;

    @Size(max = 120)
    private String profession;

    @Size(max = 120)
    private String fatherName;

    @Size(max = 120)
    private String fatherProfession;

    @Size(max = 120)
    private String motherName;

    @Size(max = 120)
    private String motherProfession;

    @Size(max = 255)
    private String siblings;

    @Size(max = 255)
    private String mama;

    @Size(max = 255)
    private String kaka;

    @Size(max = 255)
    private String address;

    @Size(max = 20)
    @Indexed(name = "idx_users_mobile")
    private String mobile;

    // Cloudinary URLs and public IDs - length not required for Mongo
    private String profilePhotoPath;
    private String aadhaarPath;
    private String profilePhotoPublicId;
    private String aadhaarPublicId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
