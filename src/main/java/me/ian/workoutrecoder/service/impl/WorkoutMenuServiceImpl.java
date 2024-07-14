package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.enums.MenuTypeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.repository.WorkoutMenuRepository;
import me.ian.workoutrecoder.service.CreateWorkoutMenuService;
import me.ian.workoutrecoder.service.WorkoutMenuService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class WorkoutMenuServiceImpl implements WorkoutMenuService {
    private final CreateWorkoutMenuService createWorkoutMenuService;

    public WorkoutMenuServiceImpl(CreateWorkoutMenuService createWorkoutMenuService) {
        this.createWorkoutMenuService = createWorkoutMenuService;
    }

    @Override
    public Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param) {
        if (MenuTypeEnum.CUSTOM.getCode() == param.getType()) {
            return createWorkoutMenuService.createCustomMenu(userId, param);
        }

        if (MenuTypeEnum.WEEKLY.getCode() == param.getType()) {
            return createWorkoutMenuService.createWeeklyMenu(userId, param);
        }

        throw new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode());
    }
}
