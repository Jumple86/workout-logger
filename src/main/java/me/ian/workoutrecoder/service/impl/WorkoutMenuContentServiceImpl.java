package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.enums.MenuTypeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;
import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuContentPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.repository.WorkoutActionRepository;
import me.ian.workoutrecoder.repository.WorkoutMenuContentRepository;
import me.ian.workoutrecoder.repository.WorkoutMenuRepository;
import me.ian.workoutrecoder.service.WorkoutMenuContentService;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class WorkoutMenuContentServiceImpl implements WorkoutMenuContentService {
    private final WorkoutMenuContentRepository workoutMenuContentRepository;
    private final WorkoutActionRepository workoutActionRepository;
    private final WorkoutMenuRepository workoutMenuRepository;

    public WorkoutMenuContentServiceImpl(WorkoutMenuContentRepository workoutMenuContentRepository,
                                         WorkoutActionRepository workoutActionRepository,
                                         WorkoutMenuRepository workoutMenuRepository) {
        this.workoutMenuContentRepository = workoutMenuContentRepository;
        this.workoutActionRepository = workoutActionRepository;
        this.workoutMenuRepository = workoutMenuRepository;
    }

    @Override
    public boolean addMenuContent(Integer userId, Integer menuId, AddWorkoutMenuContentParam param) {
        if (!isActionBelongUser(userId, param.getActionId())) {
            throw new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode(), "Not your action");
        }

        if (param.getDay() != null) {
            this.checkMenuType(menuId, po -> po.getType().equals(MenuTypeEnum.WEEKLY.getCode()));
        } else {
            this.checkMenuType(menuId, po -> po.getType().equals(MenuTypeEnum.CUSTOM.getCode()));
        }

        WorkoutMenuPO workoutMenuPO = new WorkoutMenuPO();
        workoutMenuPO.setId(menuId);

        WorkoutActionPO workoutActionPO = new WorkoutActionPO();
        workoutActionPO.setId(param.getActionId());

        try {
            WorkoutMenuContentPO workoutMenuContentPO = new WorkoutMenuContentPO();
            workoutMenuContentPO.setMenuId(workoutMenuPO);
            workoutMenuContentPO.setActionId(workoutActionPO);
            workoutMenuContentPO.setSet(param.getSet());
            workoutMenuContentPO.setWeight(param.getWeight());
            workoutMenuContentPO.setDay(param.getDay());

            workoutMenuContentRepository.save(workoutMenuContentPO);

            return true;
        } catch (Exception e) {
            throw new RestException(ApplicationResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

    private boolean isActionBelongUser(Integer userId, Integer actionId) {
        WorkoutActionPO workoutActionPO = workoutActionRepository.findById(actionId).orElseThrow(() -> new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode()));
        return workoutActionPO.getUserId().equals(userId);
    }

    private void checkMenuType(Integer menuId, Predicate<WorkoutMenuPO> predicate) {
        WorkoutMenuPO workoutMenuPO = workoutMenuRepository.findById(menuId).orElseThrow(() -> new RestException(ApplicationResponseCodeEnum.DATA_NOT_EXIST.getCode()));
        if (!predicate.test(workoutMenuPO)) {
            throw new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode(), "Menu type wrong");
        }
    }
}
