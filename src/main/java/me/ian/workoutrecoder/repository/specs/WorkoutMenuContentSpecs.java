package me.ian.workoutrecoder.repository.specs;

import me.ian.workoutrecoder.enums.WeekDayEnum;
import me.ian.workoutrecoder.model.po.WorkoutMenuContentPO;
import org.springframework.data.jpa.domain.Specification;

public class WorkoutMenuContentSpecs {

    public static Specification<WorkoutMenuContentPO> menuIdEquals(Integer menuId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("menuId").get("id"), menuId);
    }

    public static Specification<WorkoutMenuContentPO> actionIdEquals(Integer actionId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actionId").get("id"), actionId);
    }

    public static Specification<WorkoutMenuContentPO> dayEquals(WeekDayEnum day) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("day"), day);
    }
}
