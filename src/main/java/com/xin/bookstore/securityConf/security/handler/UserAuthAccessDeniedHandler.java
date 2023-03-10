package com.xin.bookstore.securityConf.security.handler;

import com.xin.bookstore.securityConf.common.utils.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler{
    // 暂无权限返回结果
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception){
        ResultUtil.responseJson(response,ResultUtil.resultCode(401,"未授权"));
    }
}