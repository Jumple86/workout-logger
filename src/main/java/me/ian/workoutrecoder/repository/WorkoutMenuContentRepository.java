package me.ian.workoutrecoder.repository;

import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.po.WorkoutMenuContentPO;
import me.ian.workoutrecoder.repository.specs.WorkoutMenuContentSpecs;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface WorkoutMenuContentRepository extends CrudRepository<WorkoutMenuContentPO, Integer>, JpaSpecificationExecutor<WorkoutMenuContentPO> {

    default long deleteBySpecification(Integer menuId, Integer actionId, WeekDayEnum day) {
        Specification<WorkoutMenuContentPO> specification = Specification.where(null);

        if (menuId != null) {
            specification = specification.and(WorkoutMenuContentSpecs.menuIdEquals(menuId));
        }

        if (actionId != null) {
            specification = specification.and(WorkoutMenuContentSpecs.actionIdEquals(actionId));
        }

        if (day != null) {
            specification = specification.and(WorkoutMenuContentSpecs.dayEquals(day));
        }

        return delete(specification);
    }
}
