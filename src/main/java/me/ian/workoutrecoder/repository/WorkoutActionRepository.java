package me.ian.workoutrecoder.repository;

import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WorkoutActionRepository extends CrudRepository<WorkoutActionPO, Integer>, JpaSpecificationExecutor<WorkoutActionPO> {

    @Query("UPDATE WorkoutActionPO po SET po.parts = :#{#workoutActionPO.parts}, po.name = :#{#workoutActionPO.name}, po.description = :#{#workoutActionPO.description} WHERE po.id = :#{#workoutActionPO.id}")
    @Modifying
    @Transactional
    int update(@Param("workoutActionPO") WorkoutActionPO po);

    @Query("UPDATE WorkoutActionPO po SET po.parts = :parts, po.name = :name, po.description = :description WHERE po.id = :id")
    @Modifying
    @Transactional
    int updateById(@Param("id") Integer id, @Param("parts") String parts, @Param("name") String name, @Param("description") String description);

}
