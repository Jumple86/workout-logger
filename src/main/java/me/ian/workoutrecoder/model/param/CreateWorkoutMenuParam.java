package me.ian.workoutrecoder.model.param;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkoutMenuParam {
    @NotEmpty
    private String name;
    @NotNull
    private Integer type;
}
