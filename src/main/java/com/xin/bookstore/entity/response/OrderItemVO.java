package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/26 - 21:45
 * @file : OrderItem.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemVO {
    private String imgUrl;
    private String name;
    private String desc;
    private Double retail;
    private Integer num;
    private Double price;
}
