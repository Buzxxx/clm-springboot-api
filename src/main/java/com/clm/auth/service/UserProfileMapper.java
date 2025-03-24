package com.clm.auth.service;

import com.clm.auth.models.UserProfile;
import com.clm.auth.models.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "fullName", expression = "java(userProfile.getFirstName() + \" \" + userProfile.getLastName())")
    @Mapping(target = "username", ignore = true)
    UserResponseDTO toResponseDTO(UserProfile userProfile);

    default UserResponseDTO toResponseDTO(UserProfile userProfile, String username) {
        UserResponseDTO userResponseDTO = toResponseDTO(userProfile);
        userResponseDTO.setUsername(username);
        return userResponseDTO;
    }
}
