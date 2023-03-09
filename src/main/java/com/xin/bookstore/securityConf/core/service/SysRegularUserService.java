package com.xin.bookstore.securityConf.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xin.bookstore.securityConf.core.entity.SysMenuEntity;
import com.xin.bookstore.securityConf.core.entity.SysRegularUserEntity;
import com.xin.bookstore.securityConf.core.entity.SysRoleEntity;

import java.util.List;

public interface SysRegularUserService extends IService<SysRegularUserEntity>  {

    /**
     * 根据用户名查询实体(普通用户)
     * @Author Sans
     * @CreateTime 2019/9/14 16:30
     * @Param  username 用户名
     * @Return SysUserEntity 用户实体
     */
    SysRegularUserEntity selectUserByName(String username);
    /**
     * 根据用户ID查询角色集合(普通用户)
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
    /**
     * 根据用户ID查询权限集合(普通用户)
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SysMenuEntity> selectSysMenuByUserId(Long userId);
}