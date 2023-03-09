package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:14
 * @file : CartItem.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class CartItem {
    @TableId("id")
    private Long id;

    @TableField("cartId")
    private Long cartId;

    @TableField("bookId")
    private Long bookId;

    private Integer num;

    private Double price;
}
