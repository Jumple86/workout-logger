package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.UserLoginParam;
import me.ian.workoutrecoder.model.param.UserParam;
import me.ian.workoutrecoder.model.vo.UserLoginVO;

public interface UserService {
    Integer register(UserParam param);

    UserLoginVO login(UserLoginParam param);
}
