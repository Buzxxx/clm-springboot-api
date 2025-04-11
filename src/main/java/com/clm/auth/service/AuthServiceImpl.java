package com.clm.auth.service;

import com.clm.auth.jwt.JwtUtil;
import com.clm.auth.models.*;
import com.clm.auth.repository.UserProfileRepository;
import com.clm.auth.repository.UserRepository;
import com.clm.shared.exception.DuplicateFieldException;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, UserProfileRepository userProfileRepository, BCryptPasswordEncoder passwordEncoder, UserProfileService userProfileService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.userProfileService = userProfileService;
    }

    @Override
    public UserResponseDTO registerUser(RegisterRequestDTO request) {
        // Validate email
        if (userProfileRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateFieldException("Email already in use");
        }

        // Validate mobile
        if (userProfileRepository.existsByMobile(request.getMobile())) {
            throw new DuplicateFieldException("Mobile number already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        if(StringUtils.isBlank(request.getUsername())) {
            user.setUsername(generateUniqueUsername(request.getUsername()));
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER); //Default Role

        UserProfile profile = new UserProfile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setMobile(request.getMobile());
        profile.setUser(user);

        user.setUserProfile(profile);
        userRepository.save(user);
        return userProfileService.getUserProfileByUsername(user.getUsername());
    }

    @Override
    public TokenResponseDTO authenticateUser(AuthRequestDTO request, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        return extracted(request.getUsername());
    }

    @Override
    public void clearCookies(HttpServletResponse response) {
        setCookie(response, "access_token", "", 0);
        setCookie(response, "refresh_token", "", 0);
    }

    @Override
    public String retrieveUsernameFromCookie(HttpServletRequest request, String cookieName) {
        String token = jwtUtil.getJwtFromCookies(request, cookieName);
        return jwtUtil.extractUsername(token);

    }

    @Override
    public String retrieveUsernameFromHeader(HttpServletRequest request) {
        String token = jwtUtil.extractAccessToken(request);
        return jwtUtil.extractUsername(token);
    }

    @Override
    public TokenResponseDTO refresh(RefreshRequestDTO request) {
        String refreshToken = request.getRefreshToken();
        if(!jwtUtil.validateToken(refreshToken))
            throw new JwtException("Invalid Token");
        String username = jwtUtil.extractUsername(refreshToken);
        return extracted(username);
    }

    @Override
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resetPasswordDTO.getUsername(), resetPasswordDTO.getOldPassword()));

        User user = userRepository.findByUsername(resetPasswordDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setPassword(resetPasswordDTO.getNewPassword());
        userRepository.save(user);
    }

    private TokenResponseDTO extracted(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accessToken = jwtUtil.generateAccessToken(user, user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user, user.getRole());

        return TokenResponseDTO
                .builder()
                        .accessToken(accessToken)
                                .refreshToken(refreshToken)
                                        .build();

    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public String generateUniqueUsername(String username) {
        do {
            username = generateRandomUsername();
        } while (userRepository.findByUsername(username).isPresent());
        return username;
    }

    public String generateRandomUsername() {
        Random random = new SecureRandom();
        int number = random.nextInt(100_000); // 0 to 99999
        return String.format("A%05d", number); // e.g. A02345
    }

}
