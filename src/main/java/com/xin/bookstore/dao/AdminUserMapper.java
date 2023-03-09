package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 18:59
 * @file : AdminUserMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    /**
    *@Description: to do
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 20:13
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.AdminUser>
    *@param: left：上界
    *@param: right：一页的大小
    */
    List<AdminUser> selectByLimit(@Param("left") Integer left, @Param("right") Integer right);

    /**
    *@Description: 通过管理员ID选出管理员的Role
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 21:12
    *@Version: v1.0
    *@Return: java.lang.String
    *@param: userId
    */
    List<String> selectRoleByUserId(@Param("userId") Long userId);

    /**
    *@Description: 根据用户ID更新状态
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 21:56
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer updateStatusByUserId(@Param("userId") Long userId, @Param("status") String status);

    /**
    *@Description: 通过用户ID选出实体
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 23:02
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.AdminUser
    *@param: userId
    */
    AdminUser selectByUserId(@Param("userId") Long userId);

    /**
    *@Description: 通过用户ID修改手机和邮箱信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 23:11
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    *@param: phone
    *@param: email
    */
    Integer updateByUserId(@Param("userId") Long userId, @Param("phone") String phone, @Param("email") String email);

    /**
    *@Description: 通过用户ID删除用户，admin表和role角色表都要删除
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 10:40
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer deleteByUserId(@Param("userId") Long userId);

    /**
    *@Description: 通过用户ID删除橘色角色
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 10:42
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer deleteRoleByUserId(@Param("userId") Long userId);

    /**
    *@Description；通过角色名选出角色ID
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 11:17
    *@Version: v1.0
    *@Return: java.lang.Long
    *@param: roleName
    */
    Long selectRoleByRoleName(@Param("roleName") String roleName);

    /**
    *@Description: 根据角色ID和用户ID插入数据
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 11:20
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: roleId
    *@param: userId
    */
    Integer insertRoleByRoleIdAndUserId(@Param("roleId") Long roleId, @Param("userId") Long userId);
}