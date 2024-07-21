package me.ian.workoutrecoder.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyWorkoutLogParam {

    @NotNull
    @JsonProperty("log_id")
    private Integer id;
    @NotBlank
    @JsonProperty("record_date")
    private String recordDate;
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
