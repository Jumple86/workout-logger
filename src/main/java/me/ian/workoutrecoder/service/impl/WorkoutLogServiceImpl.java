package me.ian.workoutrecoder.service.impl;

import org.springframework.stereotype.Service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.po.WorkoutLogPO;
import me.ian.workoutrecoder.repository.WorkoutLogRepository;
import me.ian.workoutrecoder.service.WorkoutLogService;

@Service
public class WorkoutLogServiceImpl implements WorkoutLogService {
    private final WorkoutLogRepository workoutLogRepository;

    public WorkoutLogServiceImpl(WorkoutLogRepository workoutLogRepository) {
        this.workoutLogRepository = workoutLogRepository;
    }

    @Override
    public Integer createNewWorkoutLog(Integer userId, CreateWorkoutLogParam param) {
        WorkoutLogPO po = new WorkoutLogPO();
        po.setUserId(userId);
        po.setActionId(param.getActionId());
        po.setRecordDate(param.getRecordDate());
        po.setSetNo(param.getSetNo());
        po.setTimes(param.getTimes());
        po.setWeight(param.getWeight());
        po = workoutLogRepository.save(po);
        return po.getId();
    }

    @Override
    public Boolean deleteWorkoutLog(Integer logId) {
        workoutLogRepository.deleteById(logId);
        return true;
    }

}
