package com.xin.bookstore.securityConf.security.service;

import com.xin.bookstore.securityConf.core.entity.SysAdminUserEntity;
import com.xin.bookstore.securityConf.core.service.SysAdminUserService;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 11:09
 * @file : AdminUserDetailsService.java
 * @ide : IntelliJ IDEA
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    SysAdminUserService sysAdminUserService;

    @Override
    public AdminEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysAdminUserEntity sysAdminUserEntity = sysAdminUserService.selectUserByName(username);
        if (sysAdminUserEntity != null) {
            // 组装参数
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setUserId(sysAdminUserEntity.getUserId());
            adminEntity.setUsername(sysAdminUserEntity.getUsername());
            adminEntity.setPassword(sysAdminUserEntity.getPassword());
            adminEntity.setStatus(sysAdminUserEntity.getStatus());
            return adminEntity;
        }
        return null;
    }
}
