package me.ian.workoutrecoder.controller;

import jakarta.validation.Valid;
import me.ian.workoutrecoder.controller.common.RestResponse;
import me.ian.workoutrecoder.model.param.UserParam;
import me.ian.workoutrecoder.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public RestResponse register(@RequestBody @Valid UserParam param) {
        return new RestResponse<>(userService.register(param));
    }
}
