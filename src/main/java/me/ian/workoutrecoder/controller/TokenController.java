package me.ian.workoutrecoder.controller;

import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.model.vo.RefreshTokenVO;
import me.ian.workoutrecoder.service.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/refresh")
    public RestResponse<RefreshTokenVO> refreshToken(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @RequestHeader(name = "Authorization") String bearerToken) {
        return new RestResponse<>(tokenService.refreshToken(userId, bearerToken));
    }
}
