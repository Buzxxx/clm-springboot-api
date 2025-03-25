package com.clm.auth.service;

import com.clm.auth.models.UserProfile;
import com.clm.auth.models.UserResponseDTO;
import com.clm.auth.repository.UserProfileRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserProfileMapper userProfileMapper) {
        this.userProfileRepository = userProfileRepository;
        this.userProfileMapper = userProfileMapper;
    }

    @Override
    public UserResponseDTO getUserProfileByUsername(String username) {
        UserProfile profile= userProfileRepository.findUserProfileByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User profile not found"));
        return userProfileMapper.toResponseDTO(profile, username);
    }
}
