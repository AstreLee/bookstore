package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/29 - 21:22
 * @file : OrderInfoVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderInfoVO {
    private Long orderId;

    private String username;

    private Integer num;

    private Double price;

    private List<AdminOrderInfoEachItemVO> eachItem;
}
