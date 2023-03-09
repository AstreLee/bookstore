package com.xin.bookstore.utils;

import com.xin.bookstore.securityConf.security.entity.AdminEntity;
import com.xin.bookstore.securityConf.security.entity.RegularEntity;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 15:54
 * @file : GetUserInfo.java
 * @ide : IntelliJ IDEA
 */
public class GetUserInfoUtils {
    public static Long getUserIdRegular() {
        RegularEntity regularEntity = (RegularEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return regularEntity.getUserId();
    }

    public static Long getUserIdAdmin() {
        AdminEntity adminEntity = (AdminEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminEntity.getUserId();
    }
}
