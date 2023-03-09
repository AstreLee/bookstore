package com.xin.bookstore.securityConf.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户实体
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Data
@TableName("sys_user_regular")
public class SysRegularUserEntity implements Serializable {
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
	 * 邮箱
	 */
	private String email;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String region;
	/**
	 * 详细地址
	 */
	private String details;
	/**
	 * 状态:NORMAL正常  PROHIBIT禁用
	 */
	private String status;
}