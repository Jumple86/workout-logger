package me.ian.workoutrecoder.model.po;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Data
public class UserPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "create_at", insertable = false, updatable = false)
    private Timestamp createAt;
    @Column(name = "update_at", insertable = false, updatable = false)
    private Timestamp updateAt;
}
