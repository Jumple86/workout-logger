package me.ian.workoutrecoder.service.impl;

import io.jsonwebtoken.Claims;
import me.ian.workoutrecoder.exception.AuthenticationException;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.vo.RefreshTokenVO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.JwtService;
import me.ian.workoutrecoder.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private static final String TOKEN_PREFIX = "Bearer ";

    public TokenServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public RefreshTokenVO refreshToken(Integer userId, String bearerToken) {
        String refreshToken = this.extractToken(bearerToken);
        Claims claims = jwtService.parseToken(refreshToken);
        String account = claims.getSubject();

        UserPO user = userRepository.findByAccount(account);
        if (user == null || !user.getId().equals(userId)) {
            throw new AuthenticationException("Authenticate refresh token failed");
        }

        String authenticationToken = jwtService.createAuthenticationToken(account);
        Long expiration = jwtService.getExpiration();
        RefreshTokenVO vo = new RefreshTokenVO();
        vo.setAuthenticationToken(authenticationToken);
        vo.setExpiration(expiration);

        return vo;
    }

    private String extractToken(String bearerToken) {
        return bearerToken.replace(TOKEN_PREFIX, "");
    }
}
