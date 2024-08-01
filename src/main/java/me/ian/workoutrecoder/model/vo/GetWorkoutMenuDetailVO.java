package me.ian.workoutrecoder.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class GetWorkoutMenuDetailVO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutMenuContent {
        private String actionName;
        private Double weight;
        private Integer set;
    }
}
