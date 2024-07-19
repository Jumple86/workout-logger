package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.enums.MenuTypeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;
import me.ian.workoutrecoder.repository.WorkoutMenuRepository;
import me.ian.workoutrecoder.service.WorkoutMenuService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutMenuServiceImpl implements WorkoutMenuService {
    private final WorkoutMenuRepository workoutMenuRepository;

    public WorkoutMenuServiceImpl(WorkoutMenuRepository workoutMenuRepository) {
        this.workoutMenuRepository = workoutMenuRepository;
    }

    @Override
    public Integer createWorkoutMenu(Integer userId, CreateWorkoutMenuParam param) {
        if (MenuTypeEnum.CUSTOM.getCode() == param.getType()) {
            return this.createCustomMenu(userId, param);
        }

        if (MenuTypeEnum.WEEKLY.getCode() == param.getType()) {
            return this.createWeeklyMenu(userId, param);
        }

        throw new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode());
    }

    private int createCustomMenu(Integer userId, CreateWorkoutMenuParam param) {
        WorkoutMenuPO workoutMenuPO = BeanConvertUtils.convert(param, WorkoutMenuPO.class);
        workoutMenuPO.setUserId(this.buildUserPO(userId));
        workoutMenuPO = this.doSave(workoutMenuPO);

        return workoutMenuPO.getId();
    }

    private int createWeeklyMenu(Integer userId, CreateWorkoutMenuParam param) {
        if (this.hasWeeklyMenu(userId)) {
            throw new RestException(ApplicationResponseCodeEnum.ALREADY_CREATED_WEEKLY_MENU.getCode());
        }

        WorkoutMenuPO workoutMenuPO = BeanConvertUtils.convert(param, WorkoutMenuPO.class);
        workoutMenuPO.setUserId(this.buildUserPO(userId));
        workoutMenuPO = this.doSave(workoutMenuPO);

        return workoutMenuPO.getId();
    }

    private boolean hasWeeklyMenu(Integer userId) {
        List<WorkoutMenuPO> workoutMenus = workoutMenuRepository.findBySpecification(userId, null, MenuTypeEnum.WEEKLY.getCode());
        return !workoutMenus.isEmpty();
    }

    private WorkoutMenuPO doSave(WorkoutMenuPO workoutMenuPO) {
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

    @Override
    public List<GetWorkoutMenuListVO> getWorkoutMenuList(Integer userId) {
        List<WorkoutMenuPO> workoutMenuPOs = workoutMenuRepository.findBySpecification(userId, null, null);

        List<GetWorkoutMenuListVO> result = workoutMenuPOs.stream()
                .map(po -> new GetWorkoutMenuListVO(po.getId(), po.getName()))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public boolean editWorkoutMenu(Integer menuId, Integer userId, EditWorkoutMenuParam param) {
        WorkoutMenuPO workoutMenuPO = BeanConvertUtils.convert(param, WorkoutMenuPO.class);
        workoutMenuPO.setId(menuId);
        workoutMenuPO.setUserId(this.buildUserPO(userId));

        if (MenuTypeEnum.CUSTOM.getCode() == param.getType()) {
            this.doSave(workoutMenuPO);
            return true;
        }

        if (MenuTypeEnum.WEEKLY.getCode() == param.getType()) {
            if (this.hasWeeklyMenu(userId)) {
                throw new RestException(ApplicationResponseCodeEnum.ALREADY_CREATED_WEEKLY_MENU.getCode());
            }

            this.doSave(workoutMenuPO);
            return true;
        }
        return false;
    }
}
