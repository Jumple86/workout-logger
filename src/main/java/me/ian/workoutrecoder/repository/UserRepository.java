package me.ian.workoutrecoder.repository;

import me.ian.workoutrecoder.model.po.UserPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserPO, Integer> {


}
