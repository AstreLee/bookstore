package com.xin.bookstore.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.bookstore.dao.CategoryMapper;
import com.xin.bookstore.entity.common.Category;
import com.xin.bookstore.entity.request.CategoryAddFormVO;
import com.xin.bookstore.entity.response.SelectionVO;
import com.xin.bookstore.service.CategoryService;
import com.xin.bookstore.utils.GetUserInfoUtils;
import com.xin.bookstore.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.aa.CalendarData_aa_ET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:36
 * @file : CategoryServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SnowFlake snow;

    @Override
    public List<SelectionVO> getAllCategory() {
        // 获取所有分类
        List<Category> lists = categoryMapper.selectAllCategory();
        // 定义返回结果
        List<SelectionVO> res = new ArrayList<>();
        // 获取所有出版社信息
        res.add(new SelectionVO("所有", "所有"));
        // 遍历封装结果
        for (Category item : lists) {
            SelectionVO obj = new SelectionVO();
            obj.setValue(String.valueOf(item.getCategoryId()));
            obj.setLabel(item.getName());
            res.add(obj);
        }
        return res;
    }

    @Override
    public Map<String, Object> getByLimit(Integer pageNum, Integer pageSize) {
        Integer total = categoryMapper.selectAllCategory().size();
        // 选出所有的管理员用户
        Integer left = (pageNum - 1) * pageSize;
        Integer right = pageSize;
        List<Category> lists = categoryMapper.selectByLimit(left, right);
        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", lists);
        map.put("total", total);
        return map;
    }

    @Override
    public Integer removeByCategoryId(Long CategoryId) {
        return categoryMapper.deleteByCategoryId(CategoryId);
    }

    @Override
    public Category getByCategoryId(Long categoryId) {
        return categoryMapper.selectByCategoryId(categoryId);
    }

    @Override
    public Integer updateByCategoryId(Category o) {
        return categoryMapper.updateByCategoryId(o.getCategoryId(), o.getDesc());
    }

    @Override
    public Integer addCategory(CategoryAddFormVO vo) {
        // 生成分类ID
        Long categoryId = snow.nextId();
        // 添加实体
        return categoryMapper.insert(new Category(categoryId, vo.getName(), vo.getDesc()));
    }
}
