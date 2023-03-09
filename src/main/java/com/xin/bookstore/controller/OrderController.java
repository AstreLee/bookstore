package com.xin.bookstore.controller;


import com.xin.bookstore.entity.common.*;
import com.xin.bookstore.entity.request.AdminOrderInfoVO;
import com.xin.bookstore.entity.response.*;
import com.xin.bookstore.service.*;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/25 - 10:35
 * @file : OrderCal.java
 * @ide : IntelliJ IDEA
 */
@RestController
@Api(tags = "订单管理")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("添加订单")
    @PostMapping("/order")
    public ResultVO postOrder() {
        Long orderId = orderService.postOrder();
        return new ResultVO(201, "添加订单完成！", orderId);
    }

    @ApiOperation("根据订单ID获取订单信息")
    @GetMapping("/order/info/{orderId}")
    public ResultVO getOrderInfo(@PathVariable Long orderId) {
        OrderInfoVO res = orderService.getOrderInfo(orderId);
        return new ResultVO(200, "获取订单信息成功！", res);
    }

    @ApiOperation("获取指定状态的订单")
    @GetMapping("/order/state/{state}")
    public ResultVO getAllOrder(@PathVariable Integer state) {
        List<UserOrder> allOrders = orderService.getAllOrder(state);
        return new ResultVO(200, "获取所有订单信息成功！", allOrders);
    }

    @ApiOperation("根据订单ID获取所有的订单项")
    @GetMapping("/order/item/{orderId}")
    public ResultVO getOrderItem(@PathVariable Long orderId) {
        List<OrderItemVO> res = orderService.getAllOrderItem(orderId);
        return new ResultVO(200, "获取订单列表信息成功！", res);
    }

    @ApiOperation("修改指定订单ID的状态")
    @PutMapping("/order/{orderId}/{state}")
    public ResultVO updateOrder(@PathVariable Long orderId, @PathVariable Integer state) {
        Integer count = orderService.updateOrder(orderId, state);
        if (count == 0) {
            return new ResultVO(204, "修改订单状态失败！");
        } else {
            return new ResultVO(202, "修改订单状态成功！");
        }
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/user")
    public ResultVO getUserInfo() {
        ReceiveInfoVO res = orderService.getUserInfo();
        return new ResultVO(200, "获取用户信息成功！", res);
    }

    @ApiOperation("管理员订单系统中返回的信息")
    @GetMapping("/admin/order/{state}")
    public ResultVO getOrderByState(@PathVariable Integer state) {
        List<AdminOrderInfoVO> adminOrderInfoVOList = orderService.getOrderByState(state);
        return new ResultVO(200,"查询订单信息成功", adminOrderInfoVOList);
    }
}