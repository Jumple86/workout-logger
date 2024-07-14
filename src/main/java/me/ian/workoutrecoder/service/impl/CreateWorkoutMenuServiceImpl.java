package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.enums.MenuTypeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.repository.WorkoutMenuRepository;
import me.ian.workoutrecoder.service.CreateWorkoutMenuService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateWorkoutMenuServiceImpl implements CreateWorkoutMenuService {
    private final WorkoutMenuRepository workoutMenuRepository;

    public CreateWorkoutMenuServiceImpl(WorkoutMenuRepository workoutMenuRepository) {
        this.workoutMenuRepository = workoutMenuRepository;
    }

    @Override
    public int createCustomMenu(Integer userId, CreateWorkoutMenuParam param) {
        WorkoutMenuPO workoutMenuPO = this.doSave(userId, param);

        return workoutMenuPO.getId();
    }

    @Override
    public int createWeeklyMenu(Integer userId, CreateWorkoutMenuParam param) {
        List<WorkoutMenuPO> workoutMenus = workoutMenuRepository.findBySpecification(userId, null, MenuTypeEnum.WEEKLY.getCode());
        if (!workoutMenus.isEmpty()) {
            throw new RestException(ApplicationResponseCodeEnum.ALREADY_CREATED_WEEKLY_MENU.getCode());
        }

        WorkoutMenuPO workoutMenuPO = this.doSave(userId, param);
        return workoutMenuPO.getId();
    }

    private WorkoutMenuPO doSave(Integer userId, CreateWorkoutMenuParam param) {
        WorkoutMenuPO workoutMenuPO = BeanConvertUtils.convert(param, WorkoutMenuPO.class);
        UserPO userPO = this.buildUserPO(userId);
        workoutMenuPO.setUserId(userPO);

        try {
            workoutMenuPO = workoutMenuRepository.save(workoutMenuPO);
        } catch (DataIntegrityViolationException e) {
            throw new RestException(ApplicationResponseCodeEnum.MENU_ALREADY_EXIST.getCode());
        }

        return workoutMenuPO;
    }

    private UserPO buildUserPO(Integer userId) {
        UserPO userPO = new UserPO();
        userPO.setId(userId);

        return userPO;
    }
}
