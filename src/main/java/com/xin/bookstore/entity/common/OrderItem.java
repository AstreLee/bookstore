package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:26
 * @file : OrderItem.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class OrderItem {
    @TableId("id")
    private Long id;

    @TableField("orderId")
    private Long orderId;

    @TableField("bookId")
    private Long bookId;

    private Integer num;

    private Double price;
}