package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/27 - 21:05
 * @file : AdminInfoVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminInfoVO {
    private Long userId;
    private String name;
    private String phone;
    private String email;
    private String role;
    private Boolean state;
}
