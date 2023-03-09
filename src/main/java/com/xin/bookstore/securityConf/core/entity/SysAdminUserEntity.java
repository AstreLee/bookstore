package com.xin.bookstore.securityConf.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 13:12
 * @file : SysAdminUserEntity.java
 * @ide : IntelliJ IDEA
 */
@Data
@TableName("sys_user_admin")
public class SysAdminUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    @TableId(value = "userId")
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮件
     */
    private String email;
    /**
     * 状态:NORMAL正常  PROHIBIT禁用
     */
    private String status;
}
