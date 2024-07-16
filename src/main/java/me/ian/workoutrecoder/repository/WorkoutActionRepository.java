package me.ian.workoutrecoder.repository;

import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import me.ian.workoutrecoder.repository.specs.WorkoutActionSpecs;
import org.springframework.data.jpa.domain.Specification;
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

    default List<WorkoutActionPO> findBySpecification(Integer userId, String parts, String name) {
        Specification<WorkoutActionPO> specification = Specification.where(null);

        if (userId != null) {
            specification = specification.and(WorkoutActionSpecs.userIdEquals(userId));
        }

        if (parts != null) {
            specification = specification.and(WorkoutActionSpecs.partsEquals(parts));
        }

        if (name != null) {
            specification = specification.and(WorkoutActionSpecs.nameEquals(name));
        }

        return findAll(specification);
    }

    default List<WorkoutActionPO> findByPartsIn(Integer userId, List<String> parts) {
        Specification specification = WorkoutActionSpecs.userIdEquals(userId);
        specification = specification.and(WorkoutActionSpecs.partsIn(parts));

        return findAll(specification);
    }

}
