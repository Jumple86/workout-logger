package me.ian.workoutrecoder.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RefreshTokenVO {
    @JsonProperty("authentication_token")
    private String authenticationToken;
    @JsonProperty("expiration")
    private Long expiration;
}
