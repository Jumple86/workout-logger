package me.ian.workoutrecoder.aop;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import me.ian.workoutrecoder.annotation.RequireAuth;
import me.ian.workoutrecoder.exception.AuthenticationException;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.JwtService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthAspectTest {
    @Mock
    UserRepository userRepository;
    @Mock
    ProceedingJoinPoint joinPoint;
    @Mock
    RequireAuth requireAuth;
    @Mock
    JwtService jwtService;
    @Mock
    private Claims claims;

    MockHttpServletRequest request;

    AuthAspect authAspect;

    @BeforeEach
    public void setup() {
        request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        authAspect = new AuthAspect(jwtService, userRepository);
    }

    @Test
    public void givenExpiredToken_whenAuthenticateToken_thenThrowExpiredJwtException() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";

        request.addHeader("Authorization", token);
        request.addHeader("X-User-Id", String.valueOf(1));

        when(jwtService.parseToken(jwt)).thenThrow(ExpiredJwtException.class);

        assertThrows(ExpiredJwtException.class, () -> authAspect.authenticateToken(joinPoint, requireAuth));
    }

    @Test
    public void givenWrongSubjectToken_whenAuthenticateToken_thenThrowAuthenticationException() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";

        request.addHeader("Authorization", token);
        request.addHeader("X-User-Id", String.valueOf(1));

        when(jwtService.parseToken(jwt)).thenReturn(claims);
        when(claims.getSubject()).thenReturn("test");

        UserPO userPO = new UserPO();
        userPO.setId(2);
        when(userRepository.findByAccount("test")).thenReturn(userPO);

        assertThrows(AuthenticationException.class, () -> authAspect.authenticateToken(joinPoint, requireAuth));
    }

    @Test
    public void givenCorrectToken_whenAuthenticateToken_thenJoinPointDoProceed() throws Throwable {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5M2VkNjUzZS05ZDY1LTQ1NzAtYTc5ZS0yNTY1NDAyZjM1OTkiLCJzdWIiOiJhY2NvdW50IiwiaWF0IjoxNzIwMjY4ODU5LCJleHAiOjE3MjAyNjg5MTl9.87Tr4PECyWFsfQbgt_EXwHteEGBJQJ3427BaqTftWjk";

        request.addHeader("Authorization", token);
        request.addHeader("X-User-Id", String.valueOf(1));

        when(jwtService.parseToken(jwt)).thenReturn(claims);
        when(claims.getSubject()).thenReturn("test");

        UserPO userPO = new UserPO();
        userPO.setId(1);
        when(userRepository.findByAccount("test")).thenReturn(userPO);

        when(joinPoint.proceed()).thenReturn("Success");

        Object result = authAspect.authenticateToken(joinPoint, requireAuth);
        assertEquals("Success", result);
    }
}