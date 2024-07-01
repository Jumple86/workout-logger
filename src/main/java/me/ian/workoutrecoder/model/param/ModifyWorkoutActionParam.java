package me.ian.workoutrecoder.model.param;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyWorkoutActionParam {
    @NotNull
    private Integer id;
    @NotEmpty
    private String parts;
    @NotEmpty
    private String name;
    private String description;
}
