package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.UserParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.UserService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Integer register(UserParam param) {
        String encodePassword = passwordEncoder.encode(param.getPassword());
        param.setPassword(encodePassword);

        UserPO userPO = BeanConvertUtils.convert(param, UserPO.class);
        try {
            userPO = userRepository.save(userPO);
        } catch (DataIntegrityViolationException e) {
            throw new RestException(ApplicationResponseCodeEnum.ACCOUNT_ALREADY_EXIST.getCode(), ApplicationResponseCodeEnum.ACCOUNT_ALREADY_EXIST.getMsg());
        }

        return userPO.getId();
    }
}
