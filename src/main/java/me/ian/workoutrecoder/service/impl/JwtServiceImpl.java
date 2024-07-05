package me.ian.workoutrecoder.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.ian.workoutrecoder.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {
    private final String SECRET_KEY;
    private final long AUTHENTICATION_VALID_SECONDS;
    private final long REFRESH_TOKEN_VALID_SECOND;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret,
                          @Value("${jwt.valid-time.authentication}") int validTime,
                          @Value("${jwt.valid-time.refresh}") long refreshTokenValidSecond) {
        this.SECRET_KEY = secret;
        this.AUTHENTICATION_VALID_SECONDS = Duration.ofMinutes(validTime).toSeconds();
        this.REFRESH_TOKEN_VALID_SECOND = refreshTokenValidSecond;
    }
    @Override
    public String createAuthenticationToken(String subject) {
        long expirationMillis = Instant.now()
                .plusSeconds(AUTHENTICATION_VALID_SECONDS)
                .toEpochMilli();

        return this.generateToken(subject, expirationMillis);
    }

    @Override
    public String createRefreshToken(String subject) {
        long expirationMillis = Instant.now()
                .plusSeconds(REFRESH_TOKEN_VALID_SECOND)
                .toEpochMilli();

        return this.generateToken(subject, expirationMillis);
    }

    private String generateToken(String subject, long expiration) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expiration))
                .signWith(this.getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] bytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
    }

    @Override
    public long getExpiration() {
        return this.AUTHENTICATION_VALID_SECONDS;
    }
}
