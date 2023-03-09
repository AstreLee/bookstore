package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 19:05
 * @file : RegularUserRegisterVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RegularUserRegisterVO {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 确认密码
    private String confirmedPassword;
    // 电话
    private String phone;
    // 邮件
    private String email;
    // 省
    private String province;
    // 市
    private String city;
    // 区
    private String region;
    // 详细地址
    private String details;
}
