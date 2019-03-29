package com.springboot.jwt.secret;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.SecretKeySpec;

/**
 * 服务器secrect
 *
 * @author AuroraLove
 * @date 2019/3/29
 */
public class SecretKey {


    /**
     * 服务器定义的secret只做简单演示，根据需要做持久化处理
     */
    public static final SecretKeySpec SERVER_KEY = new SecretKeySpec(Base64.decodeBase64("AuroraLove"),
            0, Base64.decodeBase64("AuroraLove").length, "AES");

}
