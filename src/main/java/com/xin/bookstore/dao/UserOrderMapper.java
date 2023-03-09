package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.UserOrder;
import com.xin.bookstore.entity.response.OrderInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:25
 * @file : UserOrderMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {
    /**
    *@Description: 根据用户ID生成订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 15:27
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: userId
    */
    Integer insertByUserIdAndOrderId(@Param("userId") Long userId,
                                     @Param("orderId") Long orderId,
                                     @Param("num") Integer num,
                                     @Param("price") Double price);

    /**
    *@Description: 根据订单Id修改订单状态
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 19:26
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: orderId
    *@param: state
    */
    Integer updateStateByOrderId(@Param("orderId") Long orderId,
                                 @Param("state") Integer state);

    /**
    *@Description: 通过订单ID获取订单商品总量和总金额
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 9:36
    *@Version: v1.0
    *@Return: OrderInfo
    *@param: orderId
    */
    OrderInfoVO selectOrderInfoByOrderId(@Param("orderId") Long orderId);

    /**
    *@Description: 通过用户ID获取所有订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 10:19
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.UserOrder
    *@param: userId
    */
    List<UserOrder> selectByUserId(@Param("userId") Long userId);

    /**
    *@Description: 通过用户ID查询满足状态条件的订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 14:51
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.UserOrder>
    *@param: userId
    *@param: state
    */
    List<UserOrder> selectByUserIdAndState(@Param("userId") Long userId, @Param("state") Integer state);

    /**
    *@Description: 通过状态选出所有的订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-29 21:33
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.UserOrder>
    *@param: state
    */
    List<UserOrder> selectByState(@Param("state") Integer state);
}
