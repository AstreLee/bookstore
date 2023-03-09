package com.xin.bookstore.controller;

import com.xin.bookstore.entity.request.AdminUserRegisterVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 18:52
 * @file : LoginRegister.java
 * @ide : IntelliJ IDEA
 */

@RestController
@Api(tags = "管理员注册")
public class AdminRegisterController {
    @Autowired
    private AdminUserService adminUserService;

    @ApiOperation("添加管理员信息")
    @PostMapping("/register/admin")
    public ResultVO registerAdmin(@RequestBody AdminUserRegisterVO vo) {
        Integer count = adminUserService.registerAdmin(vo);
        if (count == 0) {
            return new ResultVO(204, "添加用户失败！");
        } else {
            return new ResultVO(200, "添加用户成功");
        }
    }
}
