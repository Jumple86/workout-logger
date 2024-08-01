package me.ian.workoutrecoder.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class WeeklyWorkoutMenuVO extends GetWorkoutMenuDetailVO{
    private List<WorkoutMenuContent> monday;
    private List<WorkoutMenuContent> tuesday;
    private List<WorkoutMenuContent> wednesday;
    private List<WorkoutMenuContent> thursday;
    private List<WorkoutMenuContent> friday;
    private List<WorkoutMenuContent> saturday;
    private List<WorkoutMenuContent> sunday;
}
