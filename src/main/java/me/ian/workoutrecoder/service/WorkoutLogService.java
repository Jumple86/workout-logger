package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;

public interface WorkoutLogService {
    Integer createNewWorkoutLog(Integer userId, CreateWorkoutLogParam param);

    // List<GetWorkoutLogListVO> getWorkoutLogList(Integer userId);
    // GetWorkoutLogDetailVO getWorkoutLogDetail(Integer logId);
    // Boolean updatWorkoutLog(Integer userId, Integer logId, ModifyWorkoutLogParam
    // param);
    Boolean deleteWorkoutLog(Integer logId);

}
