package me.ian.workoutrecoder.service;

import java.util.List;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.param.ModifyWorkoutLogParam;
import me.ian.workoutrecoder.model.vo.GetWorkLogDetailVO;

public interface WorkoutLogService {
    Integer createNewWorkoutLog(Integer userId, CreateWorkoutLogParam param);

    List<GetWorkLogDetailVO> getWorkoutLogList(Integer userId, Integer actionId, String recodeDate);

    Boolean modifyWorkoutLog(Integer userId, Integer actionId, List<ModifyWorkoutLogParam> param);

    Boolean deleteWorkoutLog(Integer logId);

}
