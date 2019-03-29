package com.springboot.jwt.model;

import com.springboot.jwt.entity.UserEntity;
import lombok.Data;

/**
 * @author AuroraLove
 * @date 2019/3/29
 */
@Data
public class UserModel {

    private Long id;

    private String phone;

    private String name;

    private String password;

    private String token;

    public UserModel(UserEntity userEntity, String token) {
        this.id = userEntity.getId();
        this.phone = userEntity.getPhone();
        this.name = userEntity.getName();
        this.password = userEntity.getPassword();
        this.token = token;
    }

    public UserModel() {
    }
}
