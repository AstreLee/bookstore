package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/29 - 21:24
 * @file : OrderInfo_eachItemVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderInfoEachItemVO {
    private String name;

    private String desc;

    private Integer num;

    private Double price;

    private String category;

    private String publisher;
}
