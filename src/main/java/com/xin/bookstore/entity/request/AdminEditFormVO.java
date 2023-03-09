package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/27 - 23:06
 * @file : AdminEditFormVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminEditFormVO {
    private Long userId;
    private String username;
    private String phone;
    private String email;
}
