package com.clm.auth.api;

import com.clm.auth.models.*;
import com.clm.auth.service.AuthService;
import com.clm.auth.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final UserProfileService userProfileService;

    public AuthController(AuthService authService, UserProfileService userProfileService) {
        this.authService = authService;
        this.userProfileService = userProfileService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO request) {
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO request, HttpServletResponse response) {

        return ResponseEntity.ok().body(authService.authenticateUser(request, response));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
//        authService.clearCookies(response);
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(HttpServletRequest request) {
        String username = authService.retrieveUsernameFromHeader(request);
        return ResponseEntity.ok(userProfileService.getUserProfileByUsername(username));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequestDTO request) {
        return ResponseEntity.ok().body(authService.refresh(request));
        
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        authService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok("Password Reset Done");
    }
}
