package me.ian.workoutrecoder.controller;

import jakarta.validation.Valid;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.model.param.CreateWorkoutActionParam;
import me.ian.workoutrecoder.model.param.ModifyWorkoutActionParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionListVO;
import me.ian.workoutrecoder.service.WorkoutActionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/workout/action")
public class WorkoutActionController {
    private final WorkoutActionService workoutActionService;

    public WorkoutActionController(WorkoutActionService workoutActionService) {
        this.workoutActionService = workoutActionService;
    }

    @PostMapping
    public RestResponse<Integer> createNewAction(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @RequestBody @Valid CreateWorkoutActionParam param) {
        return new RestResponse<>(workoutActionService.createNewAction(userId, param));
    }

    @GetMapping
    public RestResponse<GetWorkoutActionListVO> getWorkoutActions(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @RequestParam(name = "parts", required = false) String parts,
            @RequestParam(name = "sort", defaultValue = "id") List<String> sort) {
        return new RestResponse<>(workoutActionService.getWorkoutActions(userId, parts));
    }

    @GetMapping("/multiple-parts")
    public RestResponse<GetWorkoutActionListVO> getMultiplePartsActions(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @RequestParam(name = "parts") List<String> parts) {
        return new RestResponse<>(workoutActionService.getWorkoutActionsWithMultipleParts(userId, parts));
    }

    @GetMapping("/{actionId}")
    public RestResponse getWorkoutActionDetail(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @PathVariable("actionId") Integer actionId) {
        return new RestResponse(workoutActionService.getWorkoutActionDetail(actionId));
    }

    @PutMapping
    public RestResponse<Boolean> modifyWorkoutAction(@RequestBody @Valid ModifyWorkoutActionParam param) {
        return new RestResponse<>(workoutActionService.modifyWorkoutAction(param));
    }

    @DeleteMapping("/{id}")
    public RestResponse<Boolean> deleteWorkoutAction(
            @RequestHeader(name = "X-User-Id") Integer userId,
            @PathVariable("id") Integer actionId) {
        return new RestResponse<>(workoutActionService.deleteWorkoutAction(actionId));
    }
}
