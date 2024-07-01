package me.ian.workoutrecoder.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWorkoutActionParam {
    @NotEmpty
    @JsonProperty("parts")
    private String parts;
    @NotEmpty
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
}
