package me.ian.workoutrecoder.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import me.ian.workoutrecoder.model.vo.GetWorkLogDetailVO.WorkoutLog;
import me.ian.workoutrecoder.model.vo.GetWorkoutActionListVO.WorkoutAction;

public class WorkoutLogSpecs {

    private WorkoutLogSpecs() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<WorkoutLog> joinAndActionIdEquals(Integer actionId) {
        return (root, query, criteriaBuilder) -> {
            Join<WorkoutLog, WorkoutAction> join = root.join("WorkoutAction", JoinType.INNER);
            return criteriaBuilder.and(
                    criteriaBuilder.equal(join.get("name"), actionId),
                    criteriaBuilder.equal(root.get("actionId"), actionId));
        };
    }

}
