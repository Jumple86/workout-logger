package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;

public interface CreateWorkoutMenuService {

    int createCustomMenu(Integer userId, CreateWorkoutMenuParam param);

    int createWeeklyMenu(Integer userId, CreateWorkoutMenuParam param);
}
