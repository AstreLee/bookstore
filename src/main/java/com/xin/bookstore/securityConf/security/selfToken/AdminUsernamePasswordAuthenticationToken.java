package com.xin.bookstore.securityConf.security.selfToken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 10:59
 * @file : AdminUsernamePasswordAuthenticationToken.java
 * @ide : IntelliJ IDEA
 */
public class AdminUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public AdminUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AdminUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
