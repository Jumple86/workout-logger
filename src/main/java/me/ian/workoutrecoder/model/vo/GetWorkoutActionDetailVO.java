package me.ian.workoutrecoder.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetWorkoutActionDetailVO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("parts")
    private String parts;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("create_at")
    private LocalDateTime createAt;
    @JsonProperty("update_at")
    private LocalDateTime updateAt;
}
