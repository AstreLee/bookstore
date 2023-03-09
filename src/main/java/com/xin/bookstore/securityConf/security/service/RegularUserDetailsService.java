package com.xin.bookstore.securityConf.security.service;

import com.xin.bookstore.securityConf.core.entity.SysRegularUserEntity;
import com.xin.bookstore.securityConf.core.service.SysRegularUserService;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * SpringSecurity用户的业务实现
 *
 * @Author Sans
 * @CreateTime 2019/10/1 17:21
 */
@Component
@Data
public class RegularUserDetailsService implements UserDetailsService {

    @Autowired
    private SysRegularUserService sysRegularUserService;

    /**
     * 查询用户信息
     *
     * @Author Sans
     * @CreateTime 2019/9/13 17:23
     * @Param username  用户名
     * @Return UserDetails SpringSecurity用户信息
     */
    @Override
    public RegularEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        SysRegularUserEntity sysRegularUserEntity = sysRegularUserService.selectUserByName(username);
        if (sysRegularUserEntity != null) {
            // 组装参数
            RegularEntity regularEntity = new RegularEntity();
            regularEntity.setUserId(sysRegularUserEntity.getUserId());
            regularEntity.setUsername(sysRegularUserEntity.getUsername());
            regularEntity.setPassword(sysRegularUserEntity.getPassword());
            regularEntity.setStatus(sysRegularUserEntity.getStatus());
            return regularEntity;
        }
        return null;
    }
}