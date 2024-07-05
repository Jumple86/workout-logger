package me.ian.workoutrecoder.service;

public interface JwtService {
    String createAuthenticationToken(String subject);

    String createRefreshToken(String subject);

    long getExpiration();
}
