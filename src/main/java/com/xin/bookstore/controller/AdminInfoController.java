package com.xin.bookstore.controller;

import com.xin.bookstore.entity.request.AdminAddFormVO;
import com.xin.bookstore.entity.request.AdminEditFormVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.service.AdminUserService;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/27 - 21:06
 * @file : AdminInfoCal.java
 * @ide : IntelliJ IDEA
 */

@RestController
@Api(tags = "管理员信息管理")
public class AdminInfoController {
    @Autowired
    private AdminUserService adminUserService;

    @ApiOperation("分页获取管理员信息")
    @GetMapping("/admin/info/{pageNum}/{pageSize}")
    public ResultVO getAllAdminInfo(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
//        Integer totalCount = adminUserService.list().size();
//        // 选出所有的管理员用户
//        Integer left = (pageNum - 1) * pageSize;
//        Integer right = pageSize;
//        List<AdminUser> lists = adminUserService.getByLimit(left, right);
//        // 封装返回参数
//        List<AdminInfoVO> res = new ArrayList<>();
//        // 遍历
//        for (AdminUser user : lists) {
//            AdminInfoVO obj = new AdminInfoVO();
//            BeanUtils.copyProperties(user, obj);
//            // 查询用户角色返回
//            List<String> roles = adminUserService.getRoleNameByUserId(user.getUserId());
//            // 设置用户名
//            obj.setName(user.getUsername());
//            // 封装角色
//            if (roles.get(0).equals("SUPERADMIN")) {
//                obj.setRole("超级管理员");
//            } else {
//                obj.setRole("管理员");
//            }
//            // 封装状态
//            obj.setState(user.getStatus().equals("NORMAL"));
//            // 添加进列表中
//            res.add(obj);
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("userlist", res);
//        map.put("total", totalCount);
//        // 返回结果
        Map<String, Object> map = adminUserService.getAllAdminInfo(pageNum, pageSize);
        return new ResultVO(200, "获取管理员列表成功！", map);
    }

    @ApiOperation("根据ID更新用户状态，需要超级管理员权限")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PutMapping("/admin/{id}/{state}")
    public ResultVO putAdminInfO(@PathVariable Long id, @PathVariable String state) {
        Integer count = adminUserService.putAdminInfo(id, state);
        if (count == 0) {
            return new ResultVO(204, "修改状态失败！");
        } else {
            return new ResultVO(202, "修改状态成功！");
        }
    }

    @ApiOperation("根据ID获取用户信息(封装在编辑表单对象里面)")
    @GetMapping("/admin/{id}")
    public ResultVO getByUserId(@PathVariable Long id) {
        AdminEditFormVO obj = adminUserService.getByUserId(id);
        return new ResultVO(200, "获取信息成功！", obj);
    }

    @ApiOperation("修改用户信息(手机号和邮箱号)，需要超级管理员权限")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PutMapping("/admin")
    public ResultVO updateByUserId(@RequestBody AdminEditFormVO vo) {
        Integer count = adminUserService.updateByUserId(vo);
        if (count == 0) {
            return new ResultVO(204, "修改失败！");
        } else {
            return new ResultVO(202, "修改成功！");
        }
    }

    @ApiOperation("根据用户ID删除用户，需要超级管理员权限")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResultVO deleteByUserId(@PathVariable Long id) {
        Integer count = adminUserService.deleteByUserId(id);
        if (count == 0) {
            return new ResultVO(204, "删除用户失败！");
        } else {
            return new ResultVO(203, "删除用户成功！");
        }
    }

    @ApiOperation("添加管理员用户以及用户角色表，需要超级管理员权限")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping("/admin")
    public ResultVO addUser(@RequestBody AdminAddFormVO vo) {
        Integer count = adminUserService.addUser(vo);
        if (count == 0) {
            return new ResultVO(204, "添加失败！");
        } else {
            return new ResultVO(201, "添加管理员用户成功！");
        }
    }
}
