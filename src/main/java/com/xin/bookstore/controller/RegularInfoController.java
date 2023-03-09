package com.xin.bookstore.controller;

import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.RegularUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/28 - 13:00
 * @file : RegularInfoCa.java
 * @ide : IntelliJ IDEA
 */
@RestController
@Api(tags = "普通用户管理")
public class RegularInfoController {
    @Autowired
    private RegularUserService regularUserService;

    @ApiOperation("分页获取用户信息")
    @GetMapping("/user/info/{pageNum}/{pageSize}")
    public ResultVO getAllUserInfo(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        Map<String, Object> map = regularUserService.getAllUserInfo(pageNum, pageSize);
        return new ResultVO(200, "获取用户信息成功！", map);
    }

    @ApiOperation("修改指定用户的状态")
    @PutMapping("/user/{id}/{state}")
    public ResultVO putRegularInfo(@PathVariable Long id, @PathVariable String state) {
        Integer count = regularUserService.putRegularInfo(id, state);
        if (count == 0) {
            return new ResultVO(204, "修改状态失败！");
        } else {
            return new ResultVO(202, "修改状态成功！");
        }
    }

    @ApiOperation("根据用户ID删除用户")
    @DeleteMapping("/user/{id}")
    public ResultVO deleteRegular(@PathVariable Long id) {
        Integer count = regularUserService.removeByUserId(id);
        if (count == 0) {
            return new ResultVO(204, "删除用户失败！");
        } else {
            return new ResultVO(203, "删除用户成功！");
        }
    }
}
