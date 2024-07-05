package me.ian.workoutrecoder.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginVO {
    @JsonProperty("authentication_token")
    private String authenticationToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expiration")
    private Long expiration;
    @JsonProperty("user_info")
    private UserInfoDTO userInfo;

    @NoArgsConstructor
    @Data
    public static class UserInfoDTO {
        @JsonProperty("user_id")
        private Integer userId;
        @JsonProperty("nickname")
        private String nickname;
    }
}
