package me.ian.workoutrecoder.aop;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import me.ian.workoutrecoder.annotation.RequireAuth;
import me.ian.workoutrecoder.exception.AuthenticationException;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.JwtService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private static final String TOKEN_PREFIX = "Bearer ";

    public AuthAspect(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @Around("@within(requireAuth) || @annotation(requireAuth)")
    public Object authenticateToken(ProceedingJoinPoint joinPoint, RequireAuth requireAuth) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").replace(TOKEN_PREFIX, "");
        Integer userId = request.getIntHeader("X-User-Id");

        Claims claims = jwtService.parseToken(token);
        String account = claims.getSubject();

        UserPO user = userRepository.findByAccount(account);
        if (user == null || !user.getId().equals(userId)) {
            throw new AuthenticationException();
        }

        return joinPoint.proceed();
    }
}
