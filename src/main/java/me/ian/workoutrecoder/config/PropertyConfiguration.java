package me.ian.workoutrecoder.config;

import me.ian.workoutrecoder.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {
    @Value("${jwt.secret}")
    private void setYourStaticProperty(String value) {
        JwtUtils.setSecretKey(value);
    }
}
