package com.xin.bookstore.securityConf.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.securityConf.core.entity.SysAdminUserEntity;
import com.xin.bookstore.securityConf.core.entity.SysMenuEntity;
import com.xin.bookstore.securityConf.core.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 14:33
 * @file : SysAdminUserMapper.java
 * @ide : IntelliJ IDEA
 */
@Mapper
public interface SysAdminUserMapper extends BaseMapper<SysAdminUserEntity> {
    /**
     * 通过用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysRoleEntity> 角色名集合
     */
    List<SysRoleEntity> selectSysRoleByUserId(Long userId);
    /**
     * 通过用户ID查询权限集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<SysMenuEntity> 角色名集合
     */
    List<SysMenuEntity> selectSysMenuByUserId(Long userId);
}
