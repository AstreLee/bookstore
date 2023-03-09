package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:19
 * @file : CartItemMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface CartItemMapper extends BaseMapper<CartItem> {
    /**
    *@Description: 根据购物车ID和书籍ID查看是否有该项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:32
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.CartItem：购物车项
    *@param: cartId：购物车ID
    *@param: bookId：书籍ID
    */
    CartItem selectByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId);

    /**
    *@Description: 根据书籍ID插入新的购物车项(添加购物车时使用)
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:33
    *@Version: v1.0
    *@Return: int
    *@param: cartId：购物车ID
    *@param: bookId：书籍ID
    *@param: price：书籍单价
    */
    int insertByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId, @Param("price") Double price);

    /**
    *@Description: 根据购物车ID和书籍ID更新当前项目的书籍数量和总价
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:34
    *@Version: v1.0
    *@Return: int
    *@param: cartId：购物车ID
    *@param: bookId：书籍ID
    *@param: num：直接指定数量值
    *@param: price：直接指定价格
    */
    int updateByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId, @Param("num") Integer num, @Param("price") Double price);

    /**
    *@Description: 根据购物车ID和书籍ID查看该项的选购数量
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-24 22:36
    *@Version: v1.0
    *@Return: int
    *@param: cartId：购物车ID
    *@param: bookId：书籍ID
    */
    int selectNumByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId);

    /**
    *@Description: 根据购物车ID和书籍ID删除单项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 9:14
    *@Version: v1.0
    *@Return: int
    *@param: cartId
    *@param: bookId
    */
    int deleteByCartIdAndBookId(@Param("cartId") Long cartId, @Param("bookId") Long bookId);

    /**
    *@Description: 批量删除
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 9:15
    *@Version: v1.0
    *@Return: int
    *@param: cartId
    *@param: ids
    */
    int deleteBatchCartIdAndBookId(@Param("cartId") Long cartId, @Param("ids") String ids);

    /**
    *@Description: 根据购物车ID选出所有的项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 18:54
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.CartItem>
    *@param:
    */
    List<CartItem> selectAll(@Param("cartId") Long cartId);

    /**
    *@Description: 根据购物车ID删除所有商品项
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 19:09
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: cartId
    */
    Integer deleteAll(@Param("cartId") Long cartId);
}