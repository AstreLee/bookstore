package com.xin.bookstore.securityConf.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xin.bookstore.securityConf.core.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description 用户与角色关系DAO
 * @Author Sans
 * @CreateTime 2019/9/14 15:57
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
	
}
