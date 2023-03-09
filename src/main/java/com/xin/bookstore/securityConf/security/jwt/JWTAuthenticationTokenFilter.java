package com.xin.bookstore.securityConf.security.jwt;

import com.alibaba.fastjson2.JSONObject;
import com.xin.bookstore.securityConf.common.config.JWTConfig;
import com.xin.bookstore.securityConf.common.utils.ResultUtil;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import com.xin.bookstore.securityConf.security.selfToken.AdminUsernamePasswordAuthenticationToken;
import com.xin.bookstore.securityConf.security.selfToken.RegularUsernamePasswordAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JWT接口请求校验拦截器
 * 请求接口时会进入这里验证Token是否合法和过期
 *
 * @Author Sans
 * @CreateTime 2019/10/5 16:41
 */
@Slf4j
public class JWTAuthenticationTokenFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中JWT的Token
        String tokenHeader = request.getHeader(JWTConfig.tokenHeader);
        if (null != tokenHeader && tokenHeader.startsWith(JWTConfig.tokenPrefix)) {
            try {
                // 截取JWT前缀
                String token = tokenHeader.replace(JWTConfig.tokenPrefix, "");
                // 解析JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JWTConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 首先获取自定义属性，看看是否是用户已经注销过的内容
                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
                String type = (String) claims.get("type");
                if (StringUtils.hasText(username) && StringUtils.hasText(userId)) {
                    // 获取角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
                    if (StringUtils.hasText(authority)) {
                        List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> role : authorityMap) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                    if (type.equals("regular")) {
                        //组装参数
                        RegularEntity regularEntity = new RegularEntity();
                        regularEntity.setUsername(claims.getSubject());
                        regularEntity.setUserId(Long.valueOf(claims.getId()));
                        regularEntity.setAuthorities(authorities);
                        UsernamePasswordAuthenticationToken authentication = new RegularUsernamePasswordAuthenticationToken(regularEntity, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        AdminEntity adminEntity = new AdminEntity();
                        adminEntity.setUsername(claims.getSubject());
                        adminEntity.setUserId(Long.valueOf(claims.getId()));
                        adminEntity.setAuthorities(authorities);
                        UsernamePasswordAuthenticationToken authentication = new AdminUsernamePasswordAuthenticationToken(adminEntity, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (Exception e) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("msg", "token无效");
                map.put("data", null);
                ResultUtil.responseJson(response, map);
            }
        }
        filterChain.doFilter(request, response);
    }
}