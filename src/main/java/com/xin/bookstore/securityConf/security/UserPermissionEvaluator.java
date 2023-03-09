package com.xin.bookstore.securityConf.security;

import com.xin.bookstore.securityConf.core.entity.SysAdminUserEntity;
import com.xin.bookstore.securityConf.core.entity.SysMenuEntity;
import com.xin.bookstore.securityConf.core.service.SysAdminUserService;
import com.xin.bookstore.securityConf.core.service.SysRegularUserService;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import com.xin.bookstore.securityConf.security.selfToken.AdminUsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysRegularUserService sysRegularUserService;

    @Autowired
    private SysAdminUserService sysAdminUserService;

    /**
     * hasPermission鉴权方法
     * 这里仅仅判断PreAuthorize注解中的权限表达式
     * 实际中可以根据业务需求设计数据库通过targetUrl和permission做更复杂鉴权
     * 当然targetUrl不一定是URL可以是数据Id还可以是管理员标识等,这里根据需求自行设计
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        if (authentication instanceof AdminUsernamePasswordAuthenticationToken) {
            // 获取用户信息
            AdminEntity adminEntity = (AdminEntity) authentication.getPrincipal();
            // 查询用户权限(这里可以将权限放入缓存中提升效率)
            Set<String> permissions = new HashSet<>();
            List<SysMenuEntity> sysMenuEntityList = sysAdminUserService.selectSysMenuByUserId(adminEntity.getUserId());
            for (SysMenuEntity sysMenuEntity : sysMenuEntityList) {
                permissions.add(sysMenuEntity.getPermission());
            }
            // 权限对比
            return permissions.contains(permission.toString());
        } else {
            // 获取用户信息
            RegularEntity regularEntity = (RegularEntity) authentication.getPrincipal();
            // 查询用户权限(这里可以将权限放入缓存中提升效率)
            Set<String> permissions = new HashSet<>();
            List<SysMenuEntity> sysMenuEntityList = sysRegularUserService.selectSysMenuByUserId(regularEntity.getUserId());
            for (SysMenuEntity sysMenuEntity : sysMenuEntityList) {
                permissions.add(sysMenuEntity.getPermission());
            }
            // 权限对比
            return permissions.contains(permission.toString());
        }
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}