package com.xin.bookstore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.entity.common.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:22
 * @file : CategoryMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    /**
    *@Description: 新增一条分类
     * @Author: xiaoxinxin
    *@CreatTime: 2022-11-26 16:21
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: categoryId
    *@param: name
    *@param: desc
    */
    Integer insert(@Param("categoryId") Long categoryId, @Param("name") String name, @Param("desc") String desc);

    /**
    *@Description: 根据分类名查询分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 16:23
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.Category
    *@param: categoryId
    */
    Category getIdByCategoryName(@Param("name") String name);

    /**
    *@Description: 选出所有的分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-26 16:44
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Category>
    *@param:
    */
    List<Category> selectAllCategory();

    /**
    *@Description: 分页查询出分类信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:55
    *@Version: v1.0
    *@Return: java.util.List<com.xin.bookstore.entity.common.Category>
    *@param: left
    *@param: right
    */
    List<Category> selectByLimit(@Param("left") Integer left, @Param("right") Integer right);

    /**
    *@Description: 通过分类ID删除分类
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 14:59
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: categoryId
    */
    Integer deleteByCategoryId(@Param("categoryId") Long categoryId);

    /**
    *@Description: 通过分类ID查询分类信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 15:43
    *@Version: v1.0
    *@Return: com.xin.bookstore.entity.common.Category
    *@param: categoryId
    */
    Category selectByCategoryId(@Param("categoryId") Long categoryId);

    /**
    *@Description: 通过分类ID修改信息
    *@Author: xiaoxinxin
    *@CreatTime: 2022-11-28 16:00
    *@Version: v1.0
    *@Return: java.lang.Integer
    *@param: categoryId
    */
    Integer updateByCategoryId(@Param("categoryId") Long categoryId, @Param("desc") String desc);
}
