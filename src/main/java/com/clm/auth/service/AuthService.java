package com.clm.auth.service;

import com.clm.auth.models.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    public void registerUser(RegisterRequestDTO request);
    public TokenResponseDTO authenticateUser(AuthRequestDTO request, HttpServletResponse response);
    public void clearCookies(HttpServletResponse response);
    public String retrieveUsernameFromCookie(HttpServletRequest request, String cookieName);

    String retrieveUsernameFromHeader(HttpServletRequest request);

    TokenResponseDTO refresh(RefreshRequestDTO request);

    void resetPassword(ResetPasswordDTO resetPasswordDTO);
}
