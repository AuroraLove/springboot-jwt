package com.springboot.jwt.mapper;

import com.springboot.jwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户jpa接口
 *
 * @author AuroraLove
 * @date 2019/3/29
 */
public interface UserMapper extends JpaRepository<UserEntity,Long>{

    Optional<UserEntity> findByPhone(String phone);
}
