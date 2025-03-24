package com.clm.auth.service;


import com.clm.auth.models.UserResponseDTO;

public interface UserProfileService {

    public UserResponseDTO getUserProfileByUsername(String username);
}
