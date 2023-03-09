package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/26 - 19:32
 * @file : CartItemVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemVO {
    private Long bookId;

    private String name;

    private String desc;

    private Double retail;

    private Integer num;

    private Double price;

    private String imgUrl;
}
