package com.xin.bookstore.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : joylxer
 * @date : 2022/11/29 - 20:54
 * @file : BookEditFormVO.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEditFormVO {
    private Long bookId;

    private String name;

    private String desc;

    private Integer num;

    private Double price;
}
