package me.ian.workoutrecoder.model.po;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_log")
public class WorkoutLogPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserPO userId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private WorkoutActionPO actionId;
    @Column(name = "record_date")
    private LocalDate recordDate;
    @Column(name = "set_no")
    private Integer setNo;
    @Column(name = "times")
    private Integer times;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "create_at", insertable = false, updatable = false)
    private Timestamp createAt;
    @Column(name = "update_at", insertable = false, updatable = false)
    private Timestamp updateAt;

}
