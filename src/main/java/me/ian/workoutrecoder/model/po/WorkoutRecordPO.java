package me.ian.workoutrecoder.model.po;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_record")
public class WorkoutRecordPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "action_id")
    private Integer actionId;
    @Column(name = "record_date")
    private Date recordDate;
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
