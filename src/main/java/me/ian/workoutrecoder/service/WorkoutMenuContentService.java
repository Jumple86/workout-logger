package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;

public interface WorkoutMenuContentService {
    boolean addMenuContent(Integer userId, Integer menuId, AddWorkoutMenuContentParam param);
}
