package com.clm.auth.jwt;

import com.clm.auth.models.Role;
import com.clm.auth.models.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtUtil {

    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    private static final long EXPIRATION_TIME_ACCESS = 3 * 60; // 15 minutes (in seconds)
    private static final long EXPIRATION_TIME_REFRESH = 7 * 24 * 60 * 60; // 7 days (in seconds)

    public JwtUtil(Keyloader keyloader) {
        this.publicKey = keyloader.getPublicKey();
        this.privateKey = keyloader.getPrivateKey();
    }

    public String generateAccessToken(User user, Role role) {
        return generateToken(user, role, EXPIRATION_TIME_ACCESS);
    }

    public String generateRefreshToken(User user, Role role) {
        return generateToken(user, role, EXPIRATION_TIME_REFRESH);
    }

    public String generateToken(User user, Role role, Long expirationTime) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", role)
                .issuedAt(getIssuedAtDate())
                .expiration(getExpirationTime(expirationTime))
                .signWith(privateKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
                Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token);
                return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Date getExpirationTime(long expirationTime) {
        return Date.from(ZonedDateTime.now()
                .plusSeconds(expirationTime)
                .toInstant());
    }

    public Date getIssuedAtDate() {
        return Date.from(ZonedDateTime.now().toInstant());
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public String getJwtFromCookies(HttpServletRequest request, String cookieName) {
        if(request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(cookieName))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
