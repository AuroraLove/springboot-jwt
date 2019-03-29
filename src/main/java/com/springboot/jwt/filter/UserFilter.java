package com.springboot.jwt.filter;

import lombok.Data;

/**
 * @author AuroraLove
 * @date 2019/3/29
 */
@Data
public class UserFilter {

    private Long id;

    private String phone;

    private String password;

}
