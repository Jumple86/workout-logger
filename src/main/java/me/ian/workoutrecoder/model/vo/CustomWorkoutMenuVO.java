package me.ian.workoutrecoder.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CustomWorkoutMenuVO extends GetWorkoutMenuDetailVO{
    private String name;
    private List<WorkoutMenuContent> actions;
}
