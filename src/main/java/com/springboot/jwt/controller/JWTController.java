package com.springboot.jwt.controller;

import com.springboot.jwt.annotation.Token;
import com.springboot.jwt.model.UserModel;
import com.springboot.jwt.filter.UserFilter;
import com.springboot.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JWT
 *
 * @author AuroraLove
 * @date 2019/3/29
 */
@RestController
@RequestMapping("/api/v1/rest")
public class JWTController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public UserModel login(UserFilter ufilter){
        UserModel userModel = userService.login(ufilter);
        return userModel;
    }

    @Token
    @GetMapping("/verify")
    public String verify(){
        return "token认证成功！";
    }
}
