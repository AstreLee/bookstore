package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.RegularUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 18:55
 * @file : RegularUser.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface RegularUserMapper extends BaseMapper<RegularUser> {
    /**
    *@Description: 根据用户名查找实体
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:38
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.RegularUser
    *@param: username
    */
    RegularUser selectByUsername(@Param("username") String username);

    /**
    *@Description: 通过用户ID查询用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 21:52
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.RegularUser
    *@param: userId
    */
    RegularUser selectByUserId(@Param("userId") Long userId);

    /**
    *@Description: 分页选取用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 13:12
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.RegularUser>
    *@param: left
*@param: right
    */
    List<RegularUser> selectByLimit(@Param("left") Integer left, @Param("right") Integer right);

    /**
    *@Description: 通过用户ID修改状态
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:18
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
*@param: state
    */
    Integer updateStatusByUserId(@Param("userId") Long userId, @Param("status") String status);

    /**
    *@Description: 通过用户ID删除用户名
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:23
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer deleteByUserId(@Param("userId") Long userId);

    /**
    *@Description: 通过用户ID删除用户角色
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:23
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer deleteRoleByUserId(@Param("userId") Long userId);

    /**
    *@Description: 插入普通用户所具有的的角色进sys_user_role表中
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:30
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer insertRoleByUserId(@Param("userId") Long userId);
}
