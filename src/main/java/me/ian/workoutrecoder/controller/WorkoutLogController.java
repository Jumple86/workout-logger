package me.ian.workoutrecoder.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.model.param.CreateWorkoutLogParam;
import me.ian.workoutrecoder.model.param.ModifyWorkoutLogParam;
import me.ian.workoutrecoder.model.vo.GetWorkLogDetailVO;
import me.ian.workoutrecoder.service.WorkoutLogService;

@Validated
@RestController
@RequestMapping("/workout/log")
public class WorkoutLogController {
    private final WorkoutLogService workoutLogService;

    public WorkoutLogController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @PostMapping
    public RestResponse<Integer> createWorkoutLog(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @RequestBody @Valid CreateWorkoutLogParam param) {
        return new RestResponse<>(workoutLogService.createNewWorkoutLog(userId, param));
    }

    @GetMapping
    public RestResponse<List<GetWorkLogDetailVO>> getWorkoutLogList(@RequestHeader(name = "X-User-Id") Integer userId,
            @RequestParam(name = "actionId", required = false) Integer actionId,
            @RequestParam(name = "recordDate") String recordDate) {
        return new RestResponse<>(workoutLogService.getWorkoutLogList(userId, actionId, recordDate));
    }

    @PutMapping("/{actionid}")
    public RestResponse<Boolean> modifyWorkoutLog(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @PathVariable("actionid") Integer actionId,
            @RequestBody @Valid List<ModifyWorkoutLogParam> param) {
        return new RestResponse<>(workoutLogService.modifyWorkoutLog(userId, actionId, param));
    }

    @DeleteMapping("/{logid}")
    public RestResponse<Boolean> deleteWorkoutLog(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @PathVariable("logid") Integer logId) {
        return new RestResponse<>(workoutLogService.deleteWorkoutLog(logId));
    }

}
