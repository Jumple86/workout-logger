package me.ian.workoutrecoder.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.po.WorkoutLogPO;
import me.ian.workoutrecoder.model.vo.GetWorkLogDetailVO;
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
    public GetWorkLogDetailVO getWorkoutLogList(Integer userId, Integer actionId) {
        String actionName = workoutLogRepository.getWorkoutActionName(actionId).orElse(null);
        List<WorkoutLogPO> workoutLogPOs = workoutLogRepository.findAll(null);
        Double capacity = workoutLogPOs.stream().mapToDouble(p -> p.getSetNo() * p.getTimes() * p.getWeight()).sum();
        List<GetWorkLogDetailVO.WorkoutLog> workoutLogs = workoutLogPOs.stream()
                .map(po -> new GetWorkLogDetailVO.WorkoutLog(
                        po.getRecordDate(), po.getSetNo(),
                        po.getTimes(), po.getWeight(),
                        po.getCreateAt().toLocalDateTime(), po.getUpdateAt().toLocalDateTime()))
                .collect(Collectors.toList());
        return new GetWorkLogDetailVO(actionName, capacity, workoutLogs);

    }

    @Override
    public Boolean deleteWorkoutLog(Integer logId) {
        workoutLogRepository.deleteById(logId);
        return true;
    }

}
