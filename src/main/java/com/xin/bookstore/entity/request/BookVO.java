package com.xin.bookstore.entity.request;

import lombok.Data;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 20:10
 * @file : BookVO.java
 * @ide : IntelliJ IDEA
 */
@Data
public class BookVO {
    private Long bookId;

    private String name;

    private String desc;

    private Double price;

    private Integer num;

    private String publisher;
}
