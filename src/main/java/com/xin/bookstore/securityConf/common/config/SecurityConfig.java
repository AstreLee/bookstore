package com.xin.bookstore.securityConf.common.config;

import com.xin.bookstore.securityConf.security.SelfUsernamePasswordAuthenticationFilter;
import com.xin.bookstore.securityConf.security.UserPermissionEvaluator;
import com.xin.bookstore.securityConf.security.handler.*;
import com.xin.bookstore.securityConf.security.jwt.JWTAuthenticationTokenFilter;
import com.xin.bookstore.securityConf.security.provider.AdminDaoAuthenticationProvider;
import com.xin.bookstore.securityConf.security.provider.RegularDaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解,默认是关闭的
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 自定义登录成功处理器
     */
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;
    /**
     * 自定义登录失败处理器
     */
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;
    /**
     * 自定义注销成功处理器
     */
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    /**
     * 自定义暂无权限处理器
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
    /**
     * 自定义未登录的处理器
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
    /**
     * 自定义登录逻辑验证器
     */
    @Autowired
    private RegularDaoAuthenticationProvider regularDaoAuthenticationProvider;

    @Autowired
    private AdminDaoAuthenticationProvider adminDaoAuthenticationProvider;

    // 设置加密方式
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    /**
     * 配置登录验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //这里可启用我们自己的登陆验证逻辑
        auth.authenticationProvider(regularDaoAuthenticationProvider);
        auth.authenticationProvider(adminDaoAuthenticationProvider);
    }

    @Bean
    SelfUsernamePasswordAuthenticationFilter usernamePasswordJsonAuthenticationFilter() throws Exception {
        SelfUsernamePasswordAuthenticationFilter authenticationFilter = new SelfUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(new UserLoginSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(new UserLoginFailureHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Override
    public void configure(WebSecurity web) {
        // allow Swagger URL to be accessed without authentication
        web.ignoring().antMatchers(
                "/swagger-ui/**",
                "/v3/api-docs", // swagger api json
                "/swagger-resources/configuration/ui", // 用来获取支持的动作
                "/swagger-resources", // 用来获取api-docs的URI
                "/swagger-resources/configuration/security", // 安全选项
                "/swagger-resources/**",
                "/webjars/**"
        );
    }

    /**
    *@Description: to do
    *@Author: xiaoxinxin
    *@CreatTime: 2022-12-23 20:10
    *@Version: v1.0
    *@Return: void
    *@param: http请求
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 不进行权限验证的请求或资源(从配置文件中读取)
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()
                // 其他的需要登陆后才能访问
                .anyRequest().authenticated()
                .and()
                // 配置登录地址
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                // 配置登出地址
                .logout()
                .logoutUrl("/logout")
                // 配置用户登出自定义处理类
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and()
                // 开启跨域
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                // 取消跨站请求伪造防护
                .csrf().disable();

        // 基于Token不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 设置没有权限的时候处理器
        http.exceptionHandling().accessDeniedHandler(new UserAuthAccessDeniedHandler());
        //设置未登录或登录失败时访问资源的处理方式
        http.exceptionHandling().authenticationEntryPoint(new UserAuthenticationEntryPointHandler());
        // 添加JWT过滤器，注意到一个是the filter to add一个是register
        // add直接添加对象，registry需要提前注册
        http.addFilter(new JWTAuthenticationTokenFilter(authenticationManager()));
        http.addFilterAt(usernamePasswordJsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // 解决跨域问题
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}