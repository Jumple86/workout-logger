package me.ian.workoutrecoder.model.param;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWorkoutLogParam {
    @NotNull
    @JsonProperty("action_id")
    private Integer actionId;
    @NotNull
    @JsonProperty("record_date")
    private LocalDate recordDate;
    @NotNull
    @JsonProperty("set_no")
    private Integer setNo;
    @NotNull
    @JsonProperty("times")
    private Integer times;
    @NotNull
    @JsonProperty("weight")
    private Double weight;

}
