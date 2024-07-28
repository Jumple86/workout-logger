package me.ian.workoutrecoder.controller;

import jakarta.validation.Valid;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.param.AddWorkoutMenuContentParam;
import me.ian.workoutrecoder.model.param.CreateWorkoutMenuParam;
import me.ian.workoutrecoder.model.param.EditWorkoutMenuParam;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuDetailVO;
import me.ian.workoutrecoder.model.vo.GetWorkoutMenuListVO;
import me.ian.workoutrecoder.service.WorkoutMenuFacade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout/menu")
public class WorkoutMenuController {
    private final WorkoutMenuFacade workoutMenuFacade;

    public WorkoutMenuController(WorkoutMenuFacade workoutMenuFacade) {
        this.workoutMenuFacade = workoutMenuFacade;
    }

    @PostMapping
    public RestResponse<Integer> createMenu(@RequestHeader(name = "X-User-Id") Integer userId,
                                            @RequestBody @Valid CreateWorkoutMenuParam param) {
        return new RestResponse<>(workoutMenuFacade.createWorkoutMenu(userId, param));
    }

    @GetMapping
    public RestResponse<List<GetWorkoutMenuListVO>> getMenuList(@RequestHeader(name = "X-User-Id") Integer userId) {
        return new RestResponse<>(workoutMenuFacade.getWorkoutMenuList(userId));
    }

    @PutMapping("/{menuId}")
    public RestResponse<Boolean> editWorkoutMenu(@RequestHeader(name = "X-User-Id") Integer userId,
                                                 @PathVariable("menuId") Integer menuId,
                                                 @RequestBody @Valid EditWorkoutMenuParam param) {
        return new RestResponse<>(workoutMenuFacade.editWorkoutMenu(menuId, userId, param));
    }

    @DeleteMapping("/{menuId}")
    public RestResponse<Boolean> deleteWorkoutMenu(@RequestHeader(name = "X-User-Id") Integer userId,
                                                   @PathVariable("menuId") Integer menuId) {
        return new RestResponse<>(workoutMenuFacade.deleteWorkoutMenu(userId, menuId));
    }

    @PostMapping("/{menuId}/action")
    public RestResponse<Boolean> addWorkoutMenuContent(@RequestHeader(name = "X-User-Id") Integer userId,
                                                       @PathVariable("menuId") Integer menuId,
                                                       @RequestBody @Valid AddWorkoutMenuContentParam param) {
        return new RestResponse<>(workoutMenuFacade.addMenuContent(userId, menuId, param));
    }

    @DeleteMapping("/{menuId}/action/{actionId}")
    public RestResponse<Boolean> deleteWorkoutMenuContent(@RequestHeader(name = "X-User-Id") Integer userId,
                                                          @PathVariable("menuId") Integer menuId,
                                                          @PathVariable("actionId") Integer actionId,
                                                          @RequestParam(value = "day", required = false)WeekDayEnum day) {
        return new RestResponse<>(workoutMenuFacade.deleteMenuContent(menuId, actionId, day));
    }

    @GetMapping("/{menuId}")
    public RestResponse<GetWorkoutMenuDetailVO> getWorkoutMenuDetail(@RequestHeader(name = "X-User-Id") Integer userId,
                                                                     @PathVariable("menuId") Integer menuId) {
        return new RestResponse<>(workoutMenuFacade.getWorkoutMenuDetail(userId, menuId));
    }
}
