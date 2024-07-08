package me.ian.workoutrecoder.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import me.ian.workoutrecoder.model.po.WorkoutLogPO;

public interface WorkoutLogRepository
        extends CrudRepository<WorkoutLogPO, Integer>, JpaSpecificationExecutor<WorkoutLogPO> {

}
