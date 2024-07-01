package me.ian.workoutrecoder.model.param;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserParam {
    @NotEmpty
    @Length(max = 16, message = "暱稱不得超過 {max} 個字")
    @JsonAlias("nick_name")
    @JsonProperty("nickName")
    private String nickName;
    @NotEmpty
    @Length(min = 8, max = 30, message = "帳號長度須介於 {min} 到 {max} 之間")
    @JsonProperty("account")
    private String account;
    @NotEmpty
    @Length(min = 8, max = 30, message = "密碼長度須介於 {min} 到 {max} 之間")
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
}
