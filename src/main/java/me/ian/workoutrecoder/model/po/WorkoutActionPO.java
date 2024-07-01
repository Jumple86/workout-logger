package me.ian.workoutrecoder.model.po;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "workout_action")
public class WorkoutActionPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "parts")
    private String parts;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "create_at", insertable = false, updatable = false)
    private Timestamp createAt;
    @Column(name = "update_at", insertable = false, updatable = false)
    private Timestamp updateAt;
}
