package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;

import java.util.List;

public interface WorkoutMenuFacade {
    Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param);

    List<GetWorkoutMenuListVO> getWorkoutMenuList(Integer userId);

    boolean editWorkoutMenu(Integer menuId, Integer userId, EditWorkoutMenuParam param);

    boolean deleteWorkoutMenu(Integer userId, Integer menuId);

    GetWorkoutMenuDetailVO getWorkoutMenuDetail(Integer userId, Integer menuId);

    boolean addMenuContent(Integer userId, Integer menuId, AddWorkoutMenuContentParam param);

    boolean deleteMenuContent(Integer menuId, Integer actionId, WeekDayEnum day);
}
