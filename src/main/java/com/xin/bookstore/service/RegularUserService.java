package com.xin.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.bookstore.entity.common.RegularUser;
import com.xin.bookstore.entity.request.RegularUserRegisterVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 19:01
 * @file : RegularUserService.java
 * @ide : IntelliJ IDEA
 */
@Service
public interface RegularUserService {
    /**
    *@Description: 通过用户ID删除用户以及角色
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:26
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer removeByUserId(Long userId);

    /**
    *@Description: 分页查询用户信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:49
    *@Version: v1.0
    *@Return: java.util.Map<java.lang.String,java.lang.Object>
    *@param: pageNum
    *@param: pageSize
    */
    Map<String, Object> getAllUserInfo(Integer pageNum, Integer pageSize);

    /**
    *@Description: 修改ID和状态
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:55
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: id
    *@param: state
    */
    Integer putRegularInfo(Long id, String state);

    /**
    *@Description: 注册用户
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:59
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param:
    */
    Integer registerUser(RegularUserRegisterVO vo);
}
