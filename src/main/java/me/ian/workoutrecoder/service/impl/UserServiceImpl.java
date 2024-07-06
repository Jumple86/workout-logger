package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.UserLoginParam;
import me.ian.workoutrecoder.model.param.UserParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.vo.UserLoginVO;
import me.ian.workoutrecoder.repository.UserRepository;
import me.ian.workoutrecoder.service.JwtService;
import me.ian.workoutrecoder.service.UserService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
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

    @Override
    public UserLoginVO login(UserLoginParam param) {
        String account = param.getAccount();
        UserPO user = userRepository.findByAccount(account);
        if (user == null) {
            throw new RestException(ApplicationResponseCodeEnum.USER_NOT_EXIST.getCode());
        }

        if (!passwordEncoder.matches(param.getPassword(), user.getPassword())) {
            throw new RestException(ApplicationResponseCodeEnum.PASSWORD_WRONG.getCode());
        }

        UserLoginVO vo = new UserLoginVO();
        vo.setAuthenticationToken(jwtService.createAuthenticationToken(account));
        vo.setRefreshToken(jwtService.createRefreshToken(account));
        vo.setExpiration(jwtService.getExpiration());

        UserLoginVO.UserInfoDTO userInfo = new UserLoginVO.UserInfoDTO();
        userInfo.setUserId(user.getId());
        userInfo.setNickname(user.getNickname());

        vo.setUserInfo(userInfo);

        return vo;
    }
}
