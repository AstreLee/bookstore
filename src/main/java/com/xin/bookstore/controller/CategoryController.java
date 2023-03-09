package com.xin.bookstore.controller;

import com.xin.bookstore.entity.common.Category;
import com.xin.bookstore.entity.request.CategoryAddFormVO;
import com.xin.bookstore.entity.response.SelectionVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.CategoryService;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/26 - 16:17
 * @file : CategoryCal.java
 * @ide : IntelliJ IDEA
 */
@RestController
@Api(tags = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取所有分类信息(不需要登录即可获取)")
    @GetMapping("/home/category")
    public ResultVO getAllCategory() {
        List<SelectionVO> res = categoryService.getAllCategory();
        return new ResultVO(200, "获取所有分类成功！", res);
    }

    @ApiOperation("分页获取分类信息")
    @GetMapping("/category/info/{pageNum}/{pageSize}")
    public ResultVO getCategoryInfo(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Map<String, Object> map = categoryService.getByLimit(pageNum, pageSize);
        return new ResultVO(200, "获取分类信息成功！", map);
    }

    @ApiOperation("根据分类ID删除分类")
    @DeleteMapping("/category/{id}")
    public ResultVO deleteCategoryById(@PathVariable Long id) {
        Integer count = categoryService.removeByCategoryId(id);
        if (count == 0) {
            return new ResultVO(204, "删除分类失败！");
        } else {
            return new ResultVO(203, "删除分类成功！");
        }
    }

    @ApiOperation("添加分类")
    @PostMapping("/category")
    public ResultVO addCategory(@RequestBody CategoryAddFormVO vo) {
        Integer count = categoryService.addCategory(vo);
        if (count == 0) {
            return new ResultVO(204, "添加图书分类失败！");
        } else {
            return new ResultVO(201, "添加图书分类成功！");
        }
    }

    @ApiOperation("根据分类ID获取分类信息")
    @GetMapping("/category/{id}")
    public ResultVO getCategoryById(@PathVariable Long id) {
        // 根据ID查询分类信息
        Category category = categoryService.getByCategoryId(id);
        // 返回结果
        return new ResultVO(200, "获取分类信息成功！", category);
    }

    @ApiOperation("更新分类信息")
    @PutMapping("/category")
    public ResultVO updateCategoryById(@RequestBody Category o) {
        Integer count = categoryService.updateByCategoryId(o);
        if (count == 0) {
            return new ResultVO(204, "修改分类信息失败！");
        } else {
            return new ResultVO(202, "修改分类信息成功！");
        }
    }
}