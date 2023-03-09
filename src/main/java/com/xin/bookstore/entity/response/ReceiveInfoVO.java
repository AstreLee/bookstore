package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/26 - 21:48
 * @file : ReceiveInfo.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiveInfoVO {
    private String province;
    private String city;
    private String region;
    private String details;
    private String phone;
}
