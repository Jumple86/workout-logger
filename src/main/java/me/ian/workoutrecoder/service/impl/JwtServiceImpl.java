package me.ian.workoutrecoder.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import me.ian.workoutrecoder.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {
    private final String SECRET_KEY;
    private final long VALID_SECONDS;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret,
                          @Value("${jwt.valid-time}") int validTime) {
        this.SECRET_KEY = secret;
        this.VALID_SECONDS = Duration.ofMinutes(validTime).toSeconds();
    }
    @Override
    public String createUserLoginToken(String account) {
        long expirationMillis = Instant.now()
                .plusSeconds(VALID_SECONDS)
                .toEpochMilli();


        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(account)
                .setIssuedAt(new Date())
                .setExpiration(new Date(expirationMillis))
                .signWith(this.getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        byte[] bytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }
}
