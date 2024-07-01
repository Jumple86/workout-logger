package me.ian.workoutrecoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"me.ian.workoutrecoder"})
@EnableJpaRepositories(basePackages = {"me.ian.workoutrecoder"})
public class WorkoutLoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutLoggerApplication.class, args);
	}

}
