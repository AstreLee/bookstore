package com.xin.bookstore.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/28 - 23:01
 * @file : BookInfoVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfoVO {
    private Long bookId;

    private String name;

    private String desc;

    private Double price;

    private Integer num;

    private String publisher;

    private String category;

    private String imgUrl;
}
