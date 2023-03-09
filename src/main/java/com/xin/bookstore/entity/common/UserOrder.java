package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:23
 * @file : UserOrder.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class UserOrder {
    @TableId("id")
    private Long id;

    @TableField("orderId")
    private Long orderId;

    @TableField("userId")
    private Long userId;

    private Integer num;

    private Double price;

    // 订单状态
    private Integer state;
}
