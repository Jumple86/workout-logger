package me.ian.workoutrecoder.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.po.UserPO;
import me.ian.workoutrecoder.model.po.WorkoutActionPO;
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
        UserPO userPO = new UserPO();
        userPO.setId(userId);
        po.setUserId(userPO);
        WorkoutActionPO workoutActionPO = new WorkoutActionPO();
        workoutActionPO.setId(param.getActionId());
        po.setActionId(workoutActionPO);
        po.setRecordDate(param.getRecordDate());
        po.setSetNo(param.getSetNo());
        po.setTimes(param.getTimes());
        po.setWeight(param.getWeight());
        po = workoutLogRepository.save(po);
        return po.getId();
    }

    @Override
    public List<GetWorkLogDetailVO> getWorkoutLogList(Integer userId, Integer actionId, String recordDate) {
        LocalDate rDate = LocalDate.parse(recordDate);
        List<WorkoutLogPO> workoutLogPOs;
        if (actionId != null) {
            WorkoutActionPO actionPO = new WorkoutActionPO();
            actionPO.setId(actionId);
            workoutLogPOs = workoutLogRepository.findByActionIdAndRecordDate(actionPO, rDate);
        } else {
            workoutLogPOs = workoutLogRepository.findByRecordDate(rDate);
        }

        List<String> actionName = workoutLogPOs.stream().map(p -> p.getActionId().getName()).distinct()
                .collect(Collectors.toList());

        List<GetWorkLogDetailVO> result = new ArrayList<>();
        for (int i = 0; i < actionName.size(); i++) {
            String actionNameStr = actionName.get(i);
            Double capacity = workoutLogPOs.stream().filter(p -> p.getActionId().getName().equals(actionNameStr))
                    .mapToDouble(p -> p.getSetNo() * p.getTimes() * p.getWeight())
                    .sum();
            List<GetWorkLogDetailVO.WorkoutLog> workoutLogs = workoutLogPOs.stream()
                    .filter(p -> p.getActionId().getName().equals(actionNameStr))
                    .map(po -> new GetWorkLogDetailVO.WorkoutLog(
                            po.getRecordDate(), po.getSetNo(),
                            po.getTimes(), po.getWeight(),
                            po.getCreateAt().toLocalDateTime(), po.getUpdateAt().toLocalDateTime()))
                    .collect(Collectors.toList());
            result.add(new GetWorkLogDetailVO(actionNameStr, capacity, workoutLogs));
        }
        return result;
    }

    @Override
    public Boolean deleteWorkoutLog(Integer logId) {
        workoutLogRepository.deleteById(logId);
        return true;
    }

}
