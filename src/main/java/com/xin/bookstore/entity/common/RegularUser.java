package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 18:51
 * @file : RegularUser.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
@TableName("sys_user_regular")
public class RegularUser {
    @TableId("userId")
    private Long userId;
    // 用户名
    private String username;
    // 密码
    private String password;
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
    // 用户状态
    private String status;
}
