package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.vo.RefreshTokenVO;

public interface TokenService {
    RefreshTokenVO refreshToken(Integer userId, String bearerToken);
}
