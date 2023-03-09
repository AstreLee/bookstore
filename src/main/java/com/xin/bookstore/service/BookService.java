package com.xin.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.bookstore.entity.common.Book;
import com.xin.bookstore.entity.request.BookEditFormVO;
import com.xin.bookstore.entity.request.RegularAddFormVO;
import com.xin.bookstore.entity.response.BookInfoVO;
import com.xin.bookstore.entity.response.SelectionVO;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:32
 * @file : BookService.java
 * @ide : IntelliJ IDEA
 */
public interface BookService {
    /**
    *@Description: 获取所有图书封装在List中返回
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-25 22:15
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Book>
    *@param:
    */
    List<Book> getBookList();

    /**
    *@Description: 获取所有的图书出版社
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 17:23
    *@Version: v1.0
    *@Return: java.util.List<java.lang.String>
    *@param:
    */
    List<SelectionVO> getAllPublisher();

    /**
    *@Description: 通过三个条件查询到所需要的信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 19:10
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Book>
    *@param: sql1
    *@param: sql2
    *@param: sql3
    */
    List<Book> getAllByThree(String categoryId, String price, String publisher);

    /**
    *@Description: 通过图书ID获取项目
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 22:34
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.Book
    *@param: bookId
    */
    Book getByBookId(Long bookId);

    /**
    *@Description: 插入实体
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 22:47
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
*@param: name
*@param: desc
*@param: num
*@param: price
*@param: publisher
*@param: categoryId
*@param: imgUrl
    */
    Integer saveOne(RegularAddFormVO vo);

    /**
    *@Description: 通过书籍ID删除书籍
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 23:11
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
    */
    Integer removeByBookId(Long bookId);

    /**
    *@Description: 通过图书ID更新图书
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-29 21:05
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: bookId
    *@param: desc
    *@param: num
    *@param: price
    */
    Integer updateByBookId(BookEditFormVO vo);

    /**
    *@Description:  获取图书信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 18:41
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.response.BookInfoVO>
    *@param:
    */
    List<BookInfoVO> getBookInfo();
}
