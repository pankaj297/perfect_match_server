package com.shaadi.shaadi.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpsertRequest {

    @Size(max = 120)
    private String name;

    @Size(max = 20)
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
}