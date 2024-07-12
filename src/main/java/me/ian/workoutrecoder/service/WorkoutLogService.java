package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.vo.GetWorkLogDetailVO;

public interface WorkoutLogService {
    Integer createNewWorkoutLog(Integer userId, CreateWorkoutLogParam param);

    GetWorkLogDetailVO getWorkoutLogList(Integer userId, Integer actionId);

    Boolean deleteWorkoutLog(Integer logId);

}
