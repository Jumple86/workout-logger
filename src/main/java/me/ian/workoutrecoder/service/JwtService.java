package me.ian.workoutrecoder.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String createAuthenticationToken(String subject);

    String createRefreshToken(String subject);

    long getExpiration();

    Claims parseToken(String jwt);
}
