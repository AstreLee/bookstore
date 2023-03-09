package com.xin.bookstore.securityConf.common.utils;

import com.alibaba.fastjson.JSON;
import com.xin.bookstore.securityConf.common.config.JWTConfig;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.Date;

@Slf4j
public class JWTTokenUtil {
    /**
     * 私有化构造器
     */
    private JWTTokenUtil() {
    }

    // 普通用户返回token
    public static String createAccessToken(RegularEntity regularEntity) {
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 设置用户ID
                .setId(regularEntity.getUserId() + "")
                // 设置用户名
                .setSubject(regularEntity.getUsername())
                // 设置签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("xin")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(regularEntity.getAuthorities()))
                .claim("type", "regular")
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
        return token;
    }

    // 管理员用户返回token
    public static String createAccessToken(AdminEntity adminEntity) {
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 设置用户ID
                .setId(adminEntity.getUserId() + "")
                // 设置用户名
                .setSubject(adminEntity.getUsername())
                // 设置签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("xin")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(adminEntity.getAuthorities()))
                .claim("type", "admin")
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .compact();
        return token;
    }
}