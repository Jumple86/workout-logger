package me.ian.workoutrecoder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import me.ian.workoutrecoder.model.po.WorkoutLogPO;

@Repository
public interface WorkoutLogRepository
        extends CrudRepository<WorkoutLogPO, Integer>, JpaSpecificationExecutor<WorkoutLogPO> {

    @Query("SELECT DISTINCT(pa.name) FROM WorkoutLogPO po JOIN WorkoutActionPO pa ON po.actionId = pa.id WHERE po.actionId = :actionId")
    Optional<String> getWorkoutActionName(@Param("actionId") Integer actionId);

}
