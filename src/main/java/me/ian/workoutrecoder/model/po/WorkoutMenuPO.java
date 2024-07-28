package me.ian.workoutrecoder.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "workout_menu")
@ToString(exclude = "menuContent")
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
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "menuId")
    private List<WorkoutMenuContentPO> menuContent;
}
