package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.UserCart;
import com.xin.bookstore.entity.response.CartInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 22:01
 * @file : UserCartMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface UserCartMapper extends BaseMapper<UserCart> {
    /**
    *@Description: 根据用户名查询购物车ID
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:24
    *@Version: v1.0
    *@Return: java.lang.Long：购物车ID
    *@param: userId：用户ID
    */
    Long selectCartIdByUserId(@Param("userId") Long userId);

    /**
    *@Description: 根据用户名更新购物车中商品总量和总价值
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:25
    *@Version: v1.0
    *@Return: int: 受到影响的行数
    *@param: userId
    *@param: num：这个num是增加的商品数量
    *@param: price：这个price是增加的价值
    */
    Integer updateNumAndPriceByUserId(@Param("userId") Long userId, @Param("num") Integer num, @Param("price") Double price);

    /**
    *@Description: 根据用户ID查询商品信息：总量和总价值
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:27
    *@Version: v1.0
    *@Return: CartInfo：购物车的信息(包含总量和总价值)
    *@param: userId
    */
    CartInfoVO selectNumAndPriceByUserId(@Param("userId") Long userId);

    /**
    *@Description: 根据购物车ID清除购物车信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 21:27
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param:
    */
    Integer clearCart(@Param("userId") Long userId);
}
