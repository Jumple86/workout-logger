package me.ian.workoutrecoder.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWorkLogDetailVO {

    @JsonProperty("actionName")
    private String name;
    @JsonProperty("capacity")
    private Double capacity;
    @JsonProperty("workout_log")
    private List<WorkoutLog> workoutLogs;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WorkoutLog {
        @JsonProperty("record_date")
        private LocalDate recordDate;
        @JsonProperty("set_no")
        private Integer setNo;
        @JsonProperty("times")
        private Integer times;
        @JsonProperty("weight")
        private Double weight;
        @JsonProperty("create_at")
        private LocalDateTime createAt;
        @JsonProperty("update_at")
        private LocalDateTime updateAt;
    }

}
