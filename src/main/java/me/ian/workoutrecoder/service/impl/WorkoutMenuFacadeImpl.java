package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;
import me.ian.workoutrecoder.service.WorkoutMenuContentService;
import me.ian.workoutrecoder.service.WorkoutMenuFacade;
import me.ian.workoutrecoder.service.WorkoutMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutMenuFacadeImpl implements WorkoutMenuFacade {
    private final WorkoutMenuService workoutMenuService;
    private final WorkoutMenuContentService workoutMenuContentService;

    public WorkoutMenuFacadeImpl(WorkoutMenuService workoutMenuService, WorkoutMenuContentService workoutMenuContentService) {
        this.workoutMenuService = workoutMenuService;
        this.workoutMenuContentService = workoutMenuContentService;
    }

    @Override
    public Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param) {
        return workoutMenuService.createWorkoutMenu(userId, param);
    }

    @Override
    public List<GetWorkoutMenuListVO> getWorkoutMenuList(Integer userId) {
        return workoutMenuService.getWorkoutMenuList(userId);
    }

    @Override
    public boolean editWorkoutMenu(Integer menuId, Integer userId, EditWorkoutMenuParam param) {
        return workoutMenuService.editWorkoutMenu(menuId, userId, param);
    }

    @Override
    public boolean deleteWorkoutMenu(Integer userId, Integer menuId) {
        return workoutMenuService.deleteWorkoutMenu(userId, menuId);
    }

    @Override
    public GetWorkoutMenuDetailVO getWorkoutMenuDetail(Integer userId, Integer menuId) {
        return workoutMenuService.getWorkoutMenuDetail(userId, menuId);
    }

    @Override
    public boolean addMenuContent(Integer userId, Integer menuId, AddWorkoutMenuContentParam param) {
        return workoutMenuContentService.addMenuContent(userId, menuId, param);
    }

    @Override
    public boolean deleteMenuContent(Integer menuId, Integer actionId, WeekDayEnum day) {
        return workoutMenuContentService.deleteMenuContent(menuId, actionId, day);
    }
}
