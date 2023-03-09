package com.xin.bookstore.securityConf.security.provider;

import com.xin.bookstore.securityConf.core.entity.SysRoleEntity;
import com.xin.bookstore.securityConf.core.service.SysAdminUserService;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.selfToken.AdminUsernamePasswordAuthenticationToken;
import com.xin.bookstore.securityConf.security.service.AdminUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 11:17
 * @file : AdminDaoAuthenticationProvider.java
 * @ide : IntelliJ IDEA
 */
@Component
public class AdminDaoAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AdminUserDetailsService adminUserDetailsService;
    @Autowired
    private SysAdminUserService sysAdminUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        AdminEntity userInfo = adminUserDetailsService.loadUserByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userInfo.getStatus().equals("PROHIBIT")){
            throw new LockedException("该用户已被冻结");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRoleEntity> sysRoleEntityList = sysAdminUserService.selectSysRoleByUserId(userInfo.getUserId());
        for (SysRoleEntity sysRoleEntity: sysRoleEntityList){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + sysRoleEntity.getRoleName()));
        }
        userInfo.setAuthorities(authorities);
        // 进行登录
        return new AdminUsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AdminUsernamePasswordAuthenticationToken.class);
    }
}
