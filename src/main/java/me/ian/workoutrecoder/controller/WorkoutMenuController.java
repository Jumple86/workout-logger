package me.ian.workoutrecoder.controller;

import jakarta.validation.Valid;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.service.WorkoutMenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workout/menu")
public class WorkoutMenuController {
    private final WorkoutMenuService workoutMenuService;

    public WorkoutMenuController(WorkoutMenuService workoutMenuService) {
        this.workoutMenuService = workoutMenuService;
    }

    @PostMapping
    public RestResponse<Integer> createMenu(@RequestHeader(name = "X-User-Id") Integer userId,
                                            @RequestBody @Valid CreateWorkoutMenuParam param) {
        return new RestResponse<>(workoutMenuService.createWorkoutMenu(userId, param));
    }
}
