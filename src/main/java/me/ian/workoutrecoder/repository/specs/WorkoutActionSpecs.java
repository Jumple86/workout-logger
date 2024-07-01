package me.ian.workoutrecoder.repository.specs;

import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class WorkoutActionSpecs {
    public static Specification userIdEquals(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId);
    }

    public static Specification partsEquals(String parts) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("parts"), parts);
    }

    public static Specification partsIn(List<String> partsList) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(root.get("parts").in(partsList));
    }

    public static Specification orderByCreateAtAsc() {
        return (root, query, criteriaBuilder) -> {
            CriteriaQuery<?> createAt = query.orderBy(criteriaBuilder.asc(root.get("createAt")));
            return createAt.getRestriction();
        };
    }
}
