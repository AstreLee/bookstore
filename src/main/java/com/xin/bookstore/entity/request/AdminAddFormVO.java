package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/28 - 11:05
 * @file : AdminAddFormVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAddFormVO {
    private String username;

    private String password;

    private String phone;

    private String email;

    private String role;
}
