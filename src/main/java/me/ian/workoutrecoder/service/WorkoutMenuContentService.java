package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;

public interface WorkoutMenuContentService {
    boolean addMenuContent(Integer userId, Integer menuId, AddWorkoutMenuContentParam param);

    boolean deleteMenuContent(Integer menuId, Integer actionId, WeekDayEnum day);
}
