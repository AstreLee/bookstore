package com.xin.bookstore.securityConf.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.bookstore.securityConf.core.dao.SysRegularUserMapper;
import com.xin.bookstore.securityConf.core.entity.SysMenuEntity;
import com.xin.bookstore.securityConf.core.entity.SysRegularUserEntity;
import com.xin.bookstore.securityConf.core.entity.SysRoleEntity;
import com.xin.bookstore.securityConf.core.service.SysRegularUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 系统用户业务实现
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Service
public class SysRegularUserServiceImpl extends ServiceImpl<SysRegularUserMapper, SysRegularUserEntity> implements SysRegularUserService {

    /**
     * 根据用户名查询实体
     */
    @Override
    public SysRegularUserEntity selectUserByName(String username) {
        QueryWrapper<SysRegularUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRegularUserEntity::getUsername, username);
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户ID查询角色集合
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