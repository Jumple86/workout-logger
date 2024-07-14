package me.ian.workoutrecoder.model.po;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "workout_menu")
public class WorkoutMenuPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserPO userId;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private Integer type;
    @Column(name = "create_at", insertable = false, updatable = false)
    private Timestamp createAt;
    @Column(name = "update_at", insertable = false, updatable = false)
    private Timestamp updateAt;
}
