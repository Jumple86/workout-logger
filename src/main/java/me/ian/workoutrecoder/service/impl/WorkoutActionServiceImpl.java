package me.ian.workoutrecoder.service.impl;

import jakarta.annotation.PostConstruct;
import me.ian.workoutrecoder.enums.ErrorCodeEnum;
import me.ian.workoutrecoder.exception.RestException;
import me.ian.workoutrecoder.model.param.CreateWorkoutActionParam;
import me.ian.workoutrecoder.model.param.ModifyWorkoutActionParam;
import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionListVO;
import me.ian.workoutrecoder.repository.WorkoutActionRepository;
import me.ian.workoutrecoder.repository.specs.WorkoutActionSpecs;
import me.ian.workoutrecoder.service.WorkoutActionService;
import me.ian.workoutrecoder.util.JwtUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkoutActionServiceImpl implements WorkoutActionService {
    private final WorkoutActionRepository workoutActionRepository;

    public WorkoutActionServiceImpl(WorkoutActionRepository workoutActionRepository) {
        this.workoutActionRepository = workoutActionRepository;
    }

    @Override
    public Integer createNewAction(Integer userId, CreateWorkoutActionParam param) {
        WorkoutActionPO po = new WorkoutActionPO();
        po.setUserId(userId);
        po.setParts(param.getParts());
        po.setName(param.getName());
        po.setDescription(param.getDescription());

        try {
            po = workoutActionRepository.save(po);
        } catch (DataIntegrityViolationException e) {
            throw new RestException(ErrorCodeEnum.ACTION_ALREADY_EXIST.getCode());
        }

        return po.getId();
    }

    @Override
    public GetWorkoutActionListVO getWorkoutActions(Integer userId, String parts) {
        Specification specification = WorkoutActionSpecs.userIdEquals(userId);
        if (parts != null) {
            specification = specification.and(WorkoutActionSpecs.partsEquals(parts));
        }
        List<WorkoutActionPO> workoutActionPOs = workoutActionRepository.findAll(specification);
        List<GetWorkoutActionListVO.WorkoutAction> workoutActions = workoutActionPOs.stream()
                .map(po -> new GetWorkoutActionListVO.WorkoutAction(po.getId(), po.getParts(), po.getName(), po.getCreateAt().toLocalDateTime(), po.getUpdateAt().toLocalDateTime()))
                .collect(Collectors.toList());

        return new GetWorkoutActionListVO(userId, null, workoutActions);
    }

    @Override
    public GetWorkoutActionListVO getWorkoutActionsWithMultipleParts(Integer userId, List<String> parts) {
        Specification specification = WorkoutActionSpecs.userIdEquals(userId);
        specification = specification.and(WorkoutActionSpecs.partsIn(parts));

        List<WorkoutActionPO> workoutActionPOs = workoutActionRepository.findAll(specification);
        List<GetWorkoutActionListVO.WorkoutAction> workoutActions = workoutActionPOs.stream()
                .map(po -> new GetWorkoutActionListVO.WorkoutAction(po.getId(), po.getParts(), po.getName(), po.getCreateAt().toLocalDateTime(), po.getUpdateAt().toLocalDateTime()))
                .collect(Collectors.toList());

        return new GetWorkoutActionListVO(userId, null, workoutActions);
    }

    @Override
    public GetWorkoutActionDetailVO getWorkoutActionDetail(Integer actionId) {
        WorkoutActionPO workoutActionPO = workoutActionRepository.findById(actionId).orElseThrow(() -> new RestException(ErrorCodeEnum.DATA_NOT_EXIST.getCode()));

        GetWorkoutActionDetailVO vo = new GetWorkoutActionDetailVO();
        vo.setId(workoutActionPO.getId());
        vo.setParts(workoutActionPO.getParts());
        vo.setName(workoutActionPO.getName());
        vo.setDescription(workoutActionPO.getDescription());
        vo.setCreateAt(workoutActionPO.getCreateAt().toLocalDateTime());
        vo.setUpdateAt(workoutActionPO.getUpdateAt().toLocalDateTime());

        return vo;
    }

    @Override
    public Boolean modifyWorkoutAction(ModifyWorkoutActionParam param) {
        WorkoutActionPO workoutActionPO = new WorkoutActionPO();
        workoutActionPO.setId(param.getId());
        workoutActionPO.setParts(param.getParts());
        workoutActionPO.setName(param.getName());
        workoutActionPO.setDescription(param.getDescription());

        int result = workoutActionRepository.update(workoutActionPO);
//        int result = workoutActionRepository.updateById(param.getId(), param.getParts(), param.getName(), param.getDescription());
        return result > 0;
    }

    @Override
    public Boolean deleteWorkoutAction(Integer actionId) {
        workoutActionRepository.deleteById(actionId);
        return true;
    }
}
