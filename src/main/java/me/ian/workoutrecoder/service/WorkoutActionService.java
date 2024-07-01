package me.ian.workoutrecoder.service;

import me.ian.workoutrecoder.model.param.CreateWorkoutActionParam;
import me.ian.workoutrecoder.model.param.ModifyWorkoutActionParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionListVO;

import java.util.List;

public interface WorkoutActionService {

    Integer createNewAction(Integer userId, CreateWorkoutActionParam param);
    GetWorkoutActionListVO getWorkoutActions(Integer userId, String parts);
    GetWorkoutActionListVO getWorkoutActionsWithMultipleParts(Integer userId, List<String> parts);
    GetWorkoutActionDetailVO getWorkoutActionDetail(Integer actionId);
    Boolean modifyWorkoutAction(ModifyWorkoutActionParam param);
    Boolean deleteWorkoutAction(Integer actionId);
}
