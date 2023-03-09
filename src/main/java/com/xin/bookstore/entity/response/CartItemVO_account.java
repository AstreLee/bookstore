package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/26 - 22:18
 * @file : CartItemVO_account.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemVO_account {
    private String imgUrl;

    private Long bookId;

    private String name;

    private String desc;

    private Double retail;

    private Integer num;

    private Double price;
}
