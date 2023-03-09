package com.xin.bookstore.entity.common;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:21
 * @file : Category.java
 * @ide : IntelliJ IDEA
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class Category {
    @TableId("categoryId")
    private Long categoryId;

    private String name;

    private String desc;
}
