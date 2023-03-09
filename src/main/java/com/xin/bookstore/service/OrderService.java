package com.xin.bookstore.service;

import com.xin.bookstore.entity.common.UserOrder;
import com.xin.bookstore.entity.request.AdminOrderInfoVO;
import com.xin.bookstore.entity.response.OrderInfoVO;
import com.xin.bookstore.entity.response.OrderItemVO;
import com.xin.bookstore.entity.response.ReceiveInfoVO;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 19:18
 * @file : OrderService.java
 * @ide : IntelliJ IDEA
 */
public interface OrderService {
    /**
    *@Description: 添加订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:18
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param:
    */
    Long postOrder();

    /**
    *@Description: 获取订单ID获取订单信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:33
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.response.OrderInfoVO
    *@param:
    */
    OrderInfoVO getOrderInfo(Long orderId);

    /**
    *@Description: 获取指定状态的订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:35
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.UserOrder>
    *@param: state
    */
    List<UserOrder> getAllOrder(Integer state);

    /**
    *@Description: 根据订单ID获取所有订单项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:37
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.response.OrderItemVO>
    *@param: orderId
    */
    List<OrderItemVO> getAllOrderItem(Long orderId);

    /**
    *@Description: 根据订单ID和状态修改订单
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:39
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: orderId
    *@param: state
    */
    Integer updateOrder(Long orderId, Integer state);

    /**
    *@Description: 获取用户信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:41
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.response.ReceiveInfoVO
    *@param:
    */
    ReceiveInfoVO getUserInfo();

    /**
    *@Description: 通过订单状态获取订单(管理员面板)
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:44
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.request.AdminOrderInfoVO>
    *@param: state
    */
    List<AdminOrderInfoVO> getOrderByState(Integer state);
}
