package me.ian.workoutrecoder.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkoutActionListVO {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("workout_actions")
    private List<WorkoutAction> workoutActions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutAction {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("parts")
        private String parts;
        @JsonProperty("name")
        private String name;
        @JsonProperty("create_at")
        private LocalDateTime createAt;
        @JsonProperty("update_at")
        private LocalDateTime updateAt;
    }
}
