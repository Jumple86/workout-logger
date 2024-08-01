package me.ian.workoutrecoder.service.impl;

import me.ian.workoutrecoder.enums.ApplicationResponseCodeEnum;
import me.ian.workoutrecoder.enums.MenuTypeEnum;
import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuContentPO;
import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.model.vo.CustomWorkoutMenuVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;
import me.ian.workoutrecoder.model.vo.WeeklyWorkoutMenuVO;
import me.ian.workoutrecoder.repository.WorkoutMenuRepository;
import me.ian.workoutrecoder.service.WorkoutMenuService;
import me.ian.workoutrecoder.util.BeanConvertUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public boolean deleteWorkoutMenu(Integer userId, Integer menuId) {
        try {
            workoutMenuRepository.deleteById(menuId);
            return true;
        } catch (Exception e) {
            throw new RestException(ApplicationResponseCodeEnum.SYSTEM_ERROR.getCode());
        }
    }

    @Override
    public GetWorkoutMenuDetailVO getWorkoutMenuDetail(Integer userId, Integer menuId) {
        Optional<WorkoutMenuPO> menuOpt = workoutMenuRepository.findById(menuId);
        if (menuOpt.isEmpty()) {
            throw new RestException(ApplicationResponseCodeEnum.DATA_NOT_EXIST.getCode());
        }
        WorkoutMenuPO workoutMenuPO = menuOpt.get();

        if (MenuTypeEnum.CUSTOM.getCode() == workoutMenuPO.getType()) {
            CustomWorkoutMenuVO customWorkoutMenuVO = new CustomWorkoutMenuVO();

            customWorkoutMenuVO.setName(workoutMenuPO.getName());
            List<GetWorkoutMenuDetailVO.WorkoutMenuContent> actions = this.convert2MenuContent(workoutMenuPO.getMenuContent());
            customWorkoutMenuVO.setActions(actions);

            return customWorkoutMenuVO;
        }

        if (MenuTypeEnum.WEEKLY.getCode() == workoutMenuPO.getType()) {
            WeeklyWorkoutMenuVO weeklyWorkoutMenuVO = new WeeklyWorkoutMenuVO();
            Map<WeekDayEnum, List<WorkoutMenuContentPO>> dayMenuContents = workoutMenuPO.getMenuContent().stream()
                    .collect(Collectors.groupingBy(WorkoutMenuContentPO::getDay));

            weeklyWorkoutMenuVO.setMonday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.MON, new ArrayList<>())));
            weeklyWorkoutMenuVO.setTuesday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.TUE, new ArrayList<>())));
            weeklyWorkoutMenuVO.setWednesday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.WED, new ArrayList<>())));
            weeklyWorkoutMenuVO.setThursday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.THU, new ArrayList<>())));
            weeklyWorkoutMenuVO.setFriday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.FRI, new ArrayList<>())));
            weeklyWorkoutMenuVO.setSaturday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.SAT, new ArrayList<>())));
            weeklyWorkoutMenuVO.setSunday(this.convert2MenuContent(dayMenuContents.getOrDefault(WeekDayEnum.SUN, new ArrayList<>())));

            return weeklyWorkoutMenuVO;
        }
        throw new RestException(ApplicationResponseCodeEnum.PARAMETER_WRONG.getCode());
    }

    private List<GetWorkoutMenuDetailVO.WorkoutMenuContent> convert2MenuContent(List<WorkoutMenuContentPO> workoutMenuContents) {
        return workoutMenuContents.stream()
                .map(workoutMenuContentPO -> {
                    GetWorkoutMenuDetailVO.WorkoutMenuContent menuContent = new GetWorkoutMenuDetailVO.WorkoutMenuContent();
                    menuContent.setActionName(workoutMenuContentPO.getActionId().getName());
                    menuContent.setSet(workoutMenuContentPO.getSet());
                    menuContent.setWeight(workoutMenuContentPO.getWeight());

                    return menuContent;
                })
                .toList();
    }
}
