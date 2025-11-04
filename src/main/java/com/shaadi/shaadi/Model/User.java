package com.shaadi.shaadi.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", indexes = { @Index(name = "idx_users_mobile", columnList = "mobile") })
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String mobile;

    // Cloudinary URLs
    @Column(length = 500)
    private String profilePhotoPath;

    @Column(length = 500)
    private String aadhaarPath;

    // Cloudinary public IDs (for safe deletion/updates)
    @Column(length = 255)
    private String profilePhotoPublicId;

    @Column(length = 255)
    private String aadhaarPublicId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}