package com.xin.bookstore.securityConf.security.handler;

import com.xin.bookstore.securityConf.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    // 登录失败返回结果
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户名不存在"));
        } else if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "用户被冻结"));
        } else if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "密码不正确"));
        } else {
            ResultUtil.responseJson(response, ResultUtil.resultCode(500, "登录失败"));
        }
    }
}