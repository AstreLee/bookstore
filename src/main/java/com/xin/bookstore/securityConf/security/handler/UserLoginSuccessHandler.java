package com.xin.bookstore.securityConf.security.handler;

import com.xin.bookstore.securityConf.common.config.JWTConfig;
import com.xin.bookstore.securityConf.common.utils.JWTTokenUtil;
import com.xin.bookstore.securityConf.common.utils.ResultUtil;
import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import com.xin.bookstore.securityConf.security.selfToken.RegularUsernamePasswordAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    public UserLoginSuccessHandler() {}
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 组装JWT，这里需要判断一下是普通用户类型还是管理员用户类型
        if (authentication instanceof RegularUsernamePasswordAuthenticationToken) {
            RegularEntity regularEntity = (RegularEntity) authentication.getPrincipal();
            String token = JWTTokenUtil.createAccessToken(regularEntity);
            token = JWTConfig.tokenPrefix + token;
            // 封装返回参数
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("code", 200);
            resultData.put("msg", "登录成功");
            resultData.put("token", token);
            ResultUtil.responseJson(response, resultData);
        } else {
            AdminEntity adminEntity = (AdminEntity) authentication.getPrincipal();
            String token = JWTTokenUtil.createAccessToken(adminEntity);
            token = JWTConfig.tokenPrefix + token;
            // 封装返回参数
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("code", 200);
            resultData.put("msg", "登录成功");
            resultData.put("token", token);
            ResultUtil.responseJson(response, resultData);
        }
    }
}