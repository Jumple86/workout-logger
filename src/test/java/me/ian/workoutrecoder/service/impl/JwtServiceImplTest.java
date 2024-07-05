package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {

    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        jwtService = new JwtServiceImpl("IKvcenK3ZwOunElJ3m1hUyLZIzhaJSql", 30, 43200);
    }

    @Test
    public void givenAccount_whenCreateUserLoginToken_thenReturnJwtToken() {
        String jwt = jwtService.createAuthenticationToken("account");
        System.out.println(jwt);

        assertNotNull(jwt);
    }

}