package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 18:53
 * @file : AdminUser.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
@TableName("sys_user_admin")
public class AdminUser {
    @TableId("userId")
    private Long userId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String status;
}
