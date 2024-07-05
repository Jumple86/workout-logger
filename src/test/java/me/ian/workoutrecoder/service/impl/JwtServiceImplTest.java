package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceImplTest {

    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        jwtService = new JwtServiceImpl("IKvcenK3ZwOunElJ3m1hUyLZIzhaJSql", 30);
    }

    @Test
    public void givenAccount_whenCreateUserLoginToken_thenReturnJwtToken() {
        String jwtToken = jwtService.createUserLoginToken("account");
        System.out.println(jwtToken);

        assertNotNull(jwtToken);
    }

}