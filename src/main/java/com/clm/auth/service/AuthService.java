package com.clm.auth.service;

import com.clm.auth.models.AuthRequestDTO;
import com.clm.auth.models.RegisterRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    public void registerUser(RegisterRequestDTO request);
    public void authenticateUser(AuthRequestDTO request, HttpServletResponse response);
    public void clearCookies(HttpServletResponse response);
    public String retrieveUsernameFromCookie(HttpServletRequest request, String cookieName);
}
