package me.ian.workoutrecoder.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import me.ian.workoutrecoder.model.po.WorkoutLogPO;

@Repository
public interface WorkoutLogRepository
        extends CrudRepository<WorkoutLogPO, Integer>, JpaSpecificationExecutor<WorkoutLogPO> {

    List<WorkoutLogPO> findByActionIdAndRecordDate(WorkoutActionPO actionId, LocalDate recordDate);

    List<WorkoutLogPO> findByRecordDate(LocalDate recordDate);

}
