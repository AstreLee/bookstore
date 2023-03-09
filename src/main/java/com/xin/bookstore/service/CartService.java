package com.xin.bookstore.service;

import com.xin.bookstore.entity.response.CartInfoVO;
import com.xin.bookstore.entity.response.CartItemVO;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 18:50
 * @file : CartService.java
 * @ide : IntelliJ IDEA
 */
public interface CartService {
    /**
    *@Description: 添加购物车项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:50
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
    */
    Integer addCartItem(Long bookId);

    /**
    *@Description: 更新购物车商品数量
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:55
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
    *@param: targetNum
    */
    Integer updateCartItem(Long bookId, Integer targetNum);

    /**
    *@Description: 查询购物车信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:58
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.response.CartInfoVO
    *@param:
    */
    CartInfoVO getNumAndPrice();

    /**
    *@Description: 根据书籍ID删除书籍
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:59
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
    */
    Integer deleteCartItem(Long bookId);

    /**
    *@Description: 获取所有的购物车项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:02
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.response.CartItemVO>
    *@param:
    */
    List<CartItemVO> getAllCartItem();
}
