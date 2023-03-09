package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:13
 * @file : BookMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
    /**
     * @Description: 根据书籍ID查询单价
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-24 22:31
     * @Version: v1.0
     * @Return: java.lang.Double：书籍单价
     * @param: bookId：书籍ID
     */
    Double selectPriceByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 根据书籍ID查询库存量
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-24 22:32
     * @Version: v1.0
     * @Return: java.lang.Integer：书籍库存量
     * @param: bookId：书籍ID
     */
    Integer selectNumByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 获取所有图书，封装在列表中返回
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-25 22:14
     * @Version: v1.0
     * @Return: java.util.List<com.xin.bookstore.entity.common.Book>
     * @param:
     */
    List<Book> selectBookList();

    /**
     * @Description: 通过分类ID获取所有对应图书
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 16:32
     * @Version: v1.0
     * @Return: java.util.List<com.xin.bookstore.entity.common.Book>
     * @param:
     */
    List<Book> selectBookListByCategory(@Param("categoryId") Long categoryId);

    /**
     * @Description: 选出所有不同的出版社
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 17:22
     * @Version: v1.0
     * @Return: java.util.List<java.lang.String>
     * @param:
     */
    List<String> selectAllPublisher();

    /**
     * @Description: 三项连查
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 17:35
     * @Version: v1.0
     * @Return: java.util.List<com.xin.bookstore.entity.common.Book>
     * @param: name
     */
    List<Book> selectByThree(@Param("sql1") String sql1,
                             @Param("sql2") String sql2,
                             @Param("sql3") String sql3);

    /**
     * @Description: 通过bookId查询书籍名称
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 19:41
     * @Version: v1.0
     * @Return: java.lang.String
     * @param: bookId
     */
    String selectNameByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 通过bookId查询书籍描述
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 19:41
     * @Version: v1.0
     * @Return: java.lang.String
     * @param: bookId
     */
    String selectDescByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 根据图书ID选出信息
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-26 22:33
     * @Version: v1.0
     * @Return: com.xin.bookstore.entity.common.Book
     * @param: bookId
     */
    Book selectOneByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 生成或者取消订单后就要更新库存量
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-27 13:02
     * @Version: v1.0
     * @Return: java.lang.Integer
     * @param: bookId
     * @param: num
     */
    Integer updateNumByBooId(@Param("bookId") Long bookId, @Param("num") Integer num);

    /**
     * @Description: 插入书籍实体
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-28 22:37
     * @Version: v1.0
     * @Return: java.lang.Integer
     * @param: book
     */
    Integer insertOne(Long bookId, String name, String desc, Integer num, Double price, String publisher, Long categoryId, String imgUrl);

    /**
     * @Description: 通过书籍ID删除书籍
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-28 23:10
     * @Version: v1.0
     * @Return: java.lang.Integer
     * @param: bookId
     */
    Integer deleteByBookId(@Param("bookId") Long bookId);

    /**
     * @Description: 通过图书ID更新图书
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-29 21:03
     * @Version: v1.0
     * @Return: java.lang.Integer
     * @param: bookId
     * @param: desc
     * @param: num
     * @param: price
     */
    Integer updateByBookId(@Param("bookId") Long bookId, @Param("desc") String desc, @Param("num") Integer num, @Param("price") Double price);
}