package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/28 - 13:01
 * @file : RegularInfoVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularInfoVO {
    private Long userId;

    private String username;

    private String phone;

    private String email;

    private String address;

    private String details;

    private Boolean state;
}
