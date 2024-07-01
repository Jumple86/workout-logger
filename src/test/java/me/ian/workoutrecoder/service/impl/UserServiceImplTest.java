package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.model.param.UserParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserService userService;
    
    ArgumentCaptor<UserPO> userPOArgumentCaptor = ArgumentCaptor.forClass(UserPO.class);

    @BeforeEach
    public void setup() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void givenUserParam_whenDoRegister_thenReturnId() {
        String account = "test";
        String password = "test";
        String nickName = "test";

        UserParam userParam = new UserParam();
        userParam.setAccount(account);
        userParam.setPassword(password);
        userParam.setNickName(nickName);

        String encodedPassword = passwordEncoder.encode(password);

        UserPO expectUserPO = new UserPO();
        expectUserPO.setId(1);
        expectUserPO.setAccount("test");
        expectUserPO.setPassword(encodedPassword);
        expectUserPO.setNickName(nickName);

        when(userRepository.save(any(UserPO.class))).thenReturn(expectUserPO);
        Integer result = userService.register(userParam);
        verify(userRepository).save(userPOArgumentCaptor.capture());

        UserPO captoredUserPO = userPOArgumentCaptor.getValue();
        assertEquals(1, result);
        assertTrue(passwordEncoder.matches(password, captoredUserPO.getPassword()));
    }
}