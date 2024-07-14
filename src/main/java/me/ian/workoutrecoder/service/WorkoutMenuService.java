package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;

public interface WorkoutMenuService {
    Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param);
}
