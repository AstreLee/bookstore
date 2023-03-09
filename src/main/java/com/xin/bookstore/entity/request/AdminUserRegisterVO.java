package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 19:02
 * @file : AdminUserRegisterVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AdminUserRegisterVO {
    private String username;

    private String password;

    private String confirmedPassword;

    private String phone;

    private String email;
}
