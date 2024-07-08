package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;

public interface WorkoutLogService {
    Integer createNewWorkoutLog(Integer userId, CreateWorkoutLogParam param);

    Boolean deleteWorkoutLog(Integer logId);

}
