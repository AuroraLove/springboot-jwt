package com.springboot.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https协议框架测试
 *
 * @author zyu
 * @date 2019/3/29
 */
@RestController
public class HttpsPage {

    @GetMapping("/hello")
    public String getPage(){
        return "This is https test page";
    }
}
