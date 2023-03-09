package com.xin.bookstore.service;

import com.xin.bookstore.entity.request.AdminAddFormVO;
import com.xin.bookstore.entity.request.AdminEditFormVO;
import com.xin.bookstore.entity.request.AdminUserRegisterVO;

import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 17:57
 * @file : AdminUserService123.java
 * @ide : IntelliJ IDEA
 */
public interface AdminUserService {
    /**
    *@Description: 分页获取所有管理员信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:05
    *@Version: v1.0
    *@Return: java.util.Map<java.lang.String,java.lang.Object>
    *@param: pageNum：页码
    *@param: pageSize：页码大小
    */
    Map<String, Object> getAllAdminInfo(Integer pageNum, Integer pageSize);

    /**
    *@Description: 根据用户ID更新用户状态
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:08
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: id：用户ID
    *@param: state：用户状态
    */
    Integer putAdminInfo(Long id, String state);

    /**
    *@Description: 通过用户ID获取用户信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:12
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.request.AdminEditFormVO
    *@param: id
    */
    AdminEditFormVO getByUserId(Long id);

    /**
    *@Description: 通过用户ID更新用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:13
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: id
    */
    Integer updateByUserId(AdminEditFormVO vo);

    /**
    *@Description: 通过用户ID删除用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:16
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: id
    */
    Integer deleteByUserId(Long id);

    /**
    *@Description: 添加用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:18
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param:
    */
    Integer addUser(AdminAddFormVO vo);

    /**
    *@Description: 注册管理员用户(实际上这个没有用过，添加用户就行了)
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:24
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: vo
    */
    Integer registerAdmin(AdminUserRegisterVO vo);
}
