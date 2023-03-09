package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:29
 * @file : OrderItemMaper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    /**
     * @Description: 根据订单ID和书籍ID
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-25 19:04
     * @Version: v1.0
     * @Return: java.lang.Integer
     * @param: orderId
     * @param: num
     * @param: price
     */
    Integer insertByOrderIdAndBookId(@Param("orderId") Long orderId,
                                     @Param("bookId") Long bookId,
                                     @Param("num") Integer num,
                                     @Param("price") Double price);

    /**
    *@Description: 通过订单ID获取订单项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-27 9:48
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.OrderItem
    *@param: orderId
    */
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);
}
