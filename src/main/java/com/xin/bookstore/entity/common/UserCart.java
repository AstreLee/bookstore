package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : joylxer
 * @date : 2022/11/23 - 21:58
 * @file : UserCart.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_cart")
public class UserCart {
    @TableId("id")
    private Long id;

    @TableField("cartId")
    private Long cartId;

    @TableField("userId")
    private Long userId;

    private Integer num;

    private Double price;
}
