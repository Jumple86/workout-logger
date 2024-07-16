package me.ian.workoutrecoder.repository.specs;

import jakarta.persistence.criteria.CriteriaQuery;
import me.ian.workoutrecoder.model.po.WorkoutActionPO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class WorkoutActionSpecs {
    public static Specification<WorkoutActionPO> userIdEquals(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification<WorkoutActionPO> partsEquals(String parts) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("parts"), parts);
    }

    public static Specification<WorkoutActionPO> partsIn(List<String> partsList) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(root.get("parts").in(partsList));
    }

    public static Specification<WorkoutActionPO> nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification orderByCreateAtAsc() {
        return (root, query, criteriaBuilder) -> {
            CriteriaQuery<?> createAt = query.orderBy(criteriaBuilder.asc(root.get("createAt")));
            return createAt.getRestriction();
        };
    }

}
