package me.ian.workoutrecoder.repository;

import me.ian.workoutrecoder.model.po.WorkoutMenuPO;
import me.ian.workoutrecoder.repository.specs.WorkoutMenuSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutMenuRepository extends CrudRepository<WorkoutMenuPO, Integer>, JpaSpecificationExecutor<WorkoutMenuPO> {


    default List<WorkoutMenuPO> findBySpecification(Integer userId, String name, Integer type) {
        Specification specification = Specification.where(null);

        if (userId != null) {
            specification = specification.and(WorkoutMenuSpecs.userIdEquals(userId));
        }

        if (name != null) {
            specification = specification.and(WorkoutMenuSpecs.nameEquals(name));
        }

        if (type != null) {
            specification = specification.and(WorkoutMenuSpecs.typeEquals(type));
        }

        return findAll(specification);
    }
}
