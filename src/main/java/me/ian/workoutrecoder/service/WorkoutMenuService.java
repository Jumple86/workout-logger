package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;

import java.util.List;

public interface WorkoutMenuService {
    Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param);

    List<GetWorkoutMenuListVO> getWorkoutMenuList(Integer userId);

    boolean editWorkoutMenu(Integer menuId, Integer userId, EditWorkoutMenuParam param);

    boolean deleteWorkoutMenu(Integer userId, Integer menuId);
}
