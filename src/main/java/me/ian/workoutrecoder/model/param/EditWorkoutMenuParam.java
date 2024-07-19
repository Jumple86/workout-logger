package me.ian.workoutrecoder.model.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class EditWorkoutMenuParam {
    @NotBlank
    private String name;
    @NotNull
    @Range(min = 1, max = 2)
    private Integer type;
}
