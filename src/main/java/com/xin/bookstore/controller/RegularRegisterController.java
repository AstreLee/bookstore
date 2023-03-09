package com.xin.bookstore.controller;

import com.xin.bookstore.entity.request.RegularUserRegisterVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.RegularUserService;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author : joylxer
 * @date : 2022/11/22 - 16:50
 * @file : Login_Register.java
 * @ide : IntelliJ IDEA
 */

@RestController
@Api(tags = "普通用户注册")
public class RegularRegisterController {
    @Autowired
    private RegularUserService regularUserService;

    // 用户登录功能已经在SpringSecurity中定义完
    // 并且对用户名和密码等已经完成鉴定, 访问路径就是/login
    // 注意登录的时候要带上type类型指定是普通用户还是管理员用户
    // 用户注册的同时就应该生成一个购物车的CartID
    // 而且一个用户只对应着一个购物车，所以只需要一个CartID
    @ApiOperation("普通用户注册")
    @PostMapping("/register/user")
    public ResultVO registerUser(@RequestBody RegularUserRegisterVO vo) {
        Integer count = regularUserService.registerUser(vo);
        if (count == 0) {
            return new ResultVO(204, "注册失败！");
        } else {
            return new ResultVO(201, "注册成功");
        }
    }
}
