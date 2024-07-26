package me.ian.workoutrecoder.model.param;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.ian.workoutrecoder.enums.WeekDayEnum;

@Data
public class AddWorkoutMenuContentParam {
    @NotNull
    private Integer actionId;
    private WeekDayEnum day;
    @Min(1)
    private Integer set;
    @Min(0)
    private Double weight;
}
