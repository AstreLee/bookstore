package com.xin.bookstore.securityConf.security.handler;

import com.xin.bookstore.securityConf.common.config.JWTConfig;
import com.xin.bookstore.securityConf.common.utils.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {


    public UserLogoutSuccessHandler() {
    }
    // 用户登出处理类
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", 200);
        resultData.put("msg", "登出成功");
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(response, resultData);
    }
}