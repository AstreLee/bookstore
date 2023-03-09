package com.xin.bookstore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.bookstore.entity.common.Category;
import com.xin.bookstore.entity.request.CategoryAddFormVO;
import com.xin.bookstore.entity.response.SelectionVO;

import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:35
 * @file : CategoryService.java
 * @ide : IntelliJ IDEA
 */
public interface CategoryService {
    /**
    *@Description: 获取所有分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 16:45
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Category>
    *@param:
    */
    List<SelectionVO> getAllCategory();

    /**
    *@Description: 分页查询分类信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:56
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Category>
    *@param: left
    *@param: right
    */
    Map<String, Object> getByLimit(Integer pageNum, Integer pageSize);

    /**
    *@Description: 根据分类ID删除分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 15:00
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: CategoryId
    */
    Integer removeByCategoryId(Long CategoryId);

    /**
    *@Description: 通过分类ID获取分类信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 15:45
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.Category
    *@param: categoryId
    */
    Category getByCategoryId(Long categoryId);

    /**
    *@Description: 通过分类ID修改信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 16:02
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: categoryId
    *@param: desc
    */
    Integer updateByCategoryId(Category o);

    /**
    *@Description: 添加分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-06 19:12
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: vo
    */
    Integer addCategory(CategoryAddFormVO vo);
}