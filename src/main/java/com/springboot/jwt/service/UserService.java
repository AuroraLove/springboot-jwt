package com.springboot.jwt.service;

import com.springboot.jwt.entity.UserEntity;
import com.springboot.jwt.filter.UserFilter;
import com.springboot.jwt.mapper.UserMapper;
import com.springboot.jwt.model.UserModel;
import com.springboot.jwt.secret.SecretKey;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author AuroraLove
 * @date 2019/3/29
 */
@Service
public class UserService {

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserMapper userMapper;

    public UserModel login(UserFilter ufilter){
        Optional<UserEntity> user = userMapper.findByPhone(ufilter.getPhone());
        if (user.isPresent()){
            if (user.get().getPassword().equals(ufilter.getPassword())){
                //验证用户登录成功后，生成token令牌
                String token = getToken(user.get());
                logger.info("token:" + token);
                UserModel userModel = new UserModel(user.get(),token);
                return userModel;
            }
        }
        return null;
    }

    /**
     * 生成token
     * @param userEntity
     * @return
     */
    private String getToken(UserEntity userEntity) {
        //设置算法为HS256
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
                //设置header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //设置iat
                .setIssuedAt(now)
                //设置payload的键值对
                .claim("name", userEntity.getName())
                .claim("id", userEntity.getId())
                .claim("phone", userEntity.getPhone())
                .setIssuer("AuroraLove")
                //签名，需要算法和key
                .signWith(signatureAlgorithm, SecretKey.SERVER_KEY);
        String jwt = builder.compact();
        return jwt;
    }

    public boolean verify(Long id) {
        Optional<UserEntity> userEntity = userMapper.findById(id);
        return userEntity.isPresent();
    }
}
