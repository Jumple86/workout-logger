package me.ian.workoutrecoder.repository.specs;

import org.springframework.data.jpa.domain.Specification;

public class WorkoutMenuSpecs {

    public static Specification userIdEquals(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId").get("id"), userId);
    }

    public static Specification nameEquals(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification typeEquals(Integer type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }
}
