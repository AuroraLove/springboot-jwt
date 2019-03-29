package com.springboot.jwt.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author AuroraLove
 * @date 2019/3/29
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String phone;

    @Column
    private String name;

    @Column
    private String password;
}
