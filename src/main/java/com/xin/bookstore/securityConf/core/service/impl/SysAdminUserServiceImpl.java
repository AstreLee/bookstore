package com.xin.bookstore.securityConf.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.bookstore.securityConf.core.dao.SysAdminUserMapper;
import com.xin.bookstore.securityConf.core.entity.SysAdminUserEntity;
import com.xin.bookstore.securityConf.core.entity.SysMenuEntity;
import com.xin.bookstore.securityConf.core.entity.SysRoleEntity;
import com.xin.bookstore.securityConf.core.service.SysAdminUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 14:38
 * @file : SysAdminUserServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class SysAdminUserServiceImpl extends ServiceImpl<SysAdminUserMapper, SysAdminUserEntity> implements SysAdminUserService {
    /**
     * 根据用户名查询实体
     */
    @Override
    public SysAdminUserEntity selectUserByName(String username) {
        QueryWrapper<SysAdminUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysAdminUserEntity::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据用户ID选出角色
     * @param userId
     * @return
     */
    @Override
    public List<SysRoleEntity> selectSysRoleByUserId(Long userId) {
        return this.baseMapper.selectSysRoleByUserId(userId);
    }

    /**
     * 根据用户ID查询权限集合
     */
    @Override
    public List<SysMenuEntity> selectSysMenuByUserId(Long userId) {
        return this.baseMapper.selectSysMenuByUserId(userId);
    }

}
