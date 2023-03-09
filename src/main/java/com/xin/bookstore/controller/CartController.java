package com.xin.bookstore.controller;

import com.xin.bookstore.entity.response.CartInfoVO;
import com.xin.bookstore.entity.response.CartItemVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.BookService;
import com.xin.bookstore.service.CartService;
import com.xin.bookstore.utils.ArrayToSqlCollection;
import com.xin.bookstore.utils.GetUserInfoUtils;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:31
 * @file : UserCart.java
 * @ide : IntelliJ IDEA
 */
@RestController
@Api(tags = "购物车管理")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * @Description: 添加购物车数量时更新cartItem项，同时更新userCart中的总信息
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-24 22:50
     * @Version: v1.0
     * @Return: com.xin.bookstore.entity.response.ResultVO
     * @param: vo
     */
    @PostMapping("/cart/{bookId}")
    @ApiOperation("添加购物车项目")
    public ResultVO addCartItem(@PathVariable Long bookId) {
        Integer count = cartService.addCartItem(bookId);
        if (count == 0) {
            return new ResultVO(204, "库存不足！");
        } else {
            return new ResultVO(201, "添加购物车成功！");
        }
    }

    /**
     * @Description: 修改购物车中的商品数量
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-25 8:48
     * @Version: v1.0
     * @Return: com.xin.bookstore.entity.response.ResultVO
     * @param:
     */
    @PutMapping("/cart/{bookId}/{targetNum}")
    @ApiOperation("更新购物车中商品数量")
    public ResultVO updateCartItem(@PathVariable Long bookId, @PathVariable Integer targetNum) {
        Integer count = cartService.updateCartItem(bookId, targetNum);
        if (count == 0) {
            return new ResultVO(204, "修改购物车失败！");
        } else {
            return new ResultVO(202, "修改购物车成功!");
        }
    }

    /**
     * @Description: 获取购物车信息(主要是商品总量和总价值)
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-24 22:52
     * @Version: v1.0
     * @Return: com.xin.bookstore.entity.response.ResultVO
     * @param:
     */
    @GetMapping("/cart/info")
    @ApiOperation("获取购物车商品总数和总价值")
    public ResultVO getNumAndPrice() {
        CartInfoVO obj = cartService.getNumAndPrice();
        return new ResultVO(200, "查询购物车信息成功！", obj);
    }

    /**
     * @Description: 根据BookId删除书籍(单项删除)
     * @Author: xiaoxinxin
     * @CreatTime: 2022-11-24 23:15
     * @Version: v1.0
     * @Return: com.xin.bookstore.entity.response.ResultVO
     * @param:
     */
    @DeleteMapping("/cart/{bookId}")
    @ApiOperation("根据书籍ID删除购物车项")
    public ResultVO deleteCartItem(@PathVariable Long bookId) {
        Integer count = cartService.deleteCartItem(bookId);
        if (count == 0) {
            return new ResultVO(204, "删除失败！");
        } else {
        return new ResultVO(203, "删除成功!");
        }
    }

    @DeleteMapping("/cart/batch/{ids}")
    @ApiOperation("根据ID批量删除购物车项目")
    public void deleteCartItemBatch(@PathVariable Integer[] ids) {
    }

    @GetMapping("/cart/all")
    @ApiOperation("获取购物车中所有项")
    public ResultVO getAllCartItem() {
        // 封装返回类对象
        List<CartItemVO> res = cartService.getAllCartItem();
        // 返回结果
        return new ResultVO(200, "获取购物车信息成功", res);
    }
}