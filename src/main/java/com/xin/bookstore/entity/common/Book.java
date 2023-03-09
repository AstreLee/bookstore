package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:10
 * @file : Book.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class Book {
    @TableId("bookId")
    private Long bookId;

    private String name;

    private String desc;

    private Double price;

    private Integer num;

    private String publisher;

    @TableField("categoryId")
    private Long categoryId;

    @TableField("imgUrl")
    private String imgUrl;
}
