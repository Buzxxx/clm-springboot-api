package com.clm.auth.api;

import com.clm.auth.models.AuthRequestDTO;
import com.clm.auth.models.RegisterRequestDTO;
import com.clm.auth.models.UserResponseDTO;
import com.clm.auth.service.AuthService;
import com.clm.auth.service.UserProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        authService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request, HttpServletResponse response) {
        authService.authenticateUser(request, response);
        return ResponseEntity.ok("Login successful, tokens set in cookies");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        authService.clearCookies(response);
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMe(HttpServletRequest request) {
        String username = authService.retrieveUsernameFromCookie(request, "access_token");
        return ResponseEntity.ok(userProfileService.getUserProfileByUsername(username));
    }
}
