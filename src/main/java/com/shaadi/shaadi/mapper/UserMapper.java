package com.shaadi.shaadi.mapper;

import com.shaadi.shaadi.Model.User;
import com.shaadi.shaadi.dto.UserResponse;
import com.shaadi.shaadi.dto.UserUpsertRequest;


public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(UserUpsertRequest req) {
        return User.builder()
                .name(req.getName())
                .gender(req.getGender())
                .dob(req.getDob())
                .birthplace(req.getBirthplace())
                .kuldevat(req.getKuldevat())
                .gotra(req.getGotra())
                .height(req.getHeight())
                .bloodGroup(req.getBloodGroup())
                .education(req.getEducation())
                .profession(req.getProfession())
                .fatherName(req.getFatherName())
                .fatherProfession(req.getFatherProfession())
                .motherName(req.getMotherName())
                .motherProfession(req.getMotherProfession())
                .siblings(req.getSiblings())
                .mama(req.getMama())
                .kaka(req.getKaka())
                .address(req.getAddress())
                .mobile(req.getMobile())
                .build();
    }

    public static void updateEntity(User target, UserUpsertRequest req) {
        target.setName(req.getName());
        target.setGender(req.getGender());
        target.setDob(req.getDob());
        target.setBirthplace(req.getBirthplace());
        target.setKuldevat(req.getKuldevat());
        target.setGotra(req.getGotra());
        target.setHeight(req.getHeight());
        target.setBloodGroup(req.getBloodGroup());
        target.setEducation(req.getEducation());
        target.setProfession(req.getProfession());
        target.setFatherName(req.getFatherName());
        target.setFatherProfession(req.getFatherProfession());
        target.setMotherName(req.getMotherName());
        target.setMotherProfession(req.getMotherProfession());
        target.setSiblings(req.getSiblings());
        target.setMama(req.getMama());
        target.setKaka(req.getKaka());
        target.setAddress(req.getAddress());
        target.setMobile(req.getMobile());
    }

    public static UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .gender(u.getGender())
                .dob(u.getDob())
                .birthplace(u.getBirthplace())
                .kuldevat(u.getKuldevat())
                .gotra(u.getGotra())
                .height(u.getHeight())
                .bloodGroup(u.getBloodGroup())
                .education(u.getEducation())
                .profession(u.getProfession())
                .fatherName(u.getFatherName())
                .fatherProfession(u.getFatherProfession())
                .motherName(u.getMotherName())
                .motherProfession(u.getMotherProfession())
                .siblings(u.getSiblings())
                .mama(u.getMama())
                .kaka(u.getKaka())
                .address(u.getAddress())
                .mobile(u.getMobile())
                .profilePhotoPath(u.getProfilePhotoPath())
                .aadhaarPath(u.getAadhaarPath())
                // .createdAt(u.getCreatedAt())
                // .updatedAt(u.getUpdatedAt())
                .build();
    }
}