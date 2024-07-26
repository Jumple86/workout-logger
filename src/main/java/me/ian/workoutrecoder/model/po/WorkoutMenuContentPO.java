package me.ian.workoutrecoder.model.po;

import jakarta.persistence.*;
import lombok.Data;
import me.ian.workoutrecoder.enums.WeekDayEnum;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "workout_menu_content")
public class WorkoutMenuContentPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private WorkoutMenuPO menuId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private WorkoutActionPO actionId;
    @Column(name = "`set`")
    private Integer set;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private WeekDayEnum day;
    @Column(name = "create_at", insertable = false, updatable = false)
    private LocalDateTime createAt;
    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;
}
