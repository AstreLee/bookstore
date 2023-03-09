package com.xin.bookstore.service.Impl;

import com.xin.bookstore.dao.AdminUserMapper;
import com.xin.bookstore.entity.common.AdminUser;
import com.xin.bookstore.entity.request.AdminAddFormVO;
import com.xin.bookstore.entity.request.AdminEditFormVO;
import com.xin.bookstore.entity.request.AdminUserRegisterVO;
import com.xin.bookstore.entity.response.AdminInfoVO;
import com.xin.bookstore.utils.SnowFlake;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 17:58
 * @file : AdminUserService123.java
 * @ide : IntelliJ IDEA
 */
@Service
public class AdminUserServiceImpl implements com.xin.bookstore.service.AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private SnowFlake snow;

    @Override
    public Map<String, Object> getAllAdminInfo(Integer pageNum, Integer pageSize) {
        Long totalCount = adminUserMapper.selectCount(null);
        // 选出所有的管理员用户
        Integer left = (pageNum - 1) * pageSize;
        Integer right = pageSize;
        List<AdminUser> lists = adminUserMapper.selectByLimit(left, right);
        // 封装返回参数
        List<AdminInfoVO> res = new ArrayList<>();
        // 遍历
        for (AdminUser user : lists) {
            AdminInfoVO obj = new AdminInfoVO();
            BeanUtils.copyProperties(user, obj);
            // 查询用户角色返回
            List<String> roles = adminUserMapper.selectRoleByUserId(user.getUserId());
            // 设置用户名
            obj.setName(user.getUsername());
            // 封装角色
            if (roles.get(0).equals("SUPERADMIN")) {
                obj.setRole("超级管理员");
            } else {
                obj.setRole("管理员");
            }
            // 封装状态
            obj.setState(user.getStatus().equals("NORMAL"));
            // 添加进列表中
            res.add(obj);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userlist", res);
        map.put("total", totalCount);
        return map;
    }

    @Override
    public Integer putAdminInfo(Long id, String state) {
        Integer count = 0;
        if (state.equals("false")) {
            count = adminUserMapper.updateStatusByUserId(id, "PROHIBIT");
        } else {
            count = adminUserMapper.updateStatusByUserId(id, "NORMAL");
        }
        return count;
    }

    @Override
    public AdminEditFormVO getByUserId(Long id) {
        AdminUser adminUser = adminUserMapper.selectByUserId(id);
        AdminEditFormVO obj = new AdminEditFormVO();
        BeanUtils.copyProperties(adminUser, obj);
        return obj;
    }

    @Override
    public Integer updateByUserId(AdminEditFormVO vo) {
        return adminUserMapper.updateByUserId(vo.getUserId(), vo.getPhone(), vo.getEmail());
    }

    @Override
    public Integer deleteByUserId(Long id) {
        return adminUserMapper.deleteByUserId(id);
    }

    @Override
    public Integer addUser(AdminAddFormVO vo) {
        // 需要插入到admin用户表和角色表中
        // 根据雪花算法生成用户ID
        Long userId = snow.nextId();
        // 创建插入实体
        AdminUser user = new AdminUser();
        // 复制属性
        BeanUtils.copyProperties(vo, user);
        // 设置用户ID和状态，密码还得加密处理一下
        user.setUserId(userId);
        user.setStatus("NORMAL");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // 插入用户
        adminUserMapper.insert(user);
        // 获取vo中的角色名，插入到角色表中
        String roleName = vo.getRole();
        // 根据RoleName查询出对应的RoleId
        Long roleId = adminUserMapper.selectRoleByRoleName(roleName);
        // 插入到角色表当中
        return adminUserMapper.insertRoleByRoleIdAndUserId(roleId, userId);
    }

    @Override
    public Integer registerAdmin(AdminUserRegisterVO vo) {
        // 将VO转换成我们的实体类对象
        AdminUser adminUser = new AdminUser();
        BeanUtils.copyProperties(vo, adminUser);
        adminUser.setStatus("NORMAL");
        // 密码需要加密存储
        String passwd = new BCryptPasswordEncoder().encode(adminUser.getPassword());
        adminUser.setPassword(passwd);
        return adminUserMapper.insert(adminUser);
    }
}
