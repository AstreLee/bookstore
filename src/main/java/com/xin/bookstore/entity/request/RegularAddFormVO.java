package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/28 - 21:47
 * @file : RegularAddFormVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegularAddFormVO {
    private String name;

    private String desc;

    private Integer num;

    private Double price;

    private String publisher;

    private Long categoryId;

    private String imgUrl;
}
