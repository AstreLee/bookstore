package com.xin.bookstore.utils;

import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/25 - 10:28
 * @file : ArrayToSqlCollection.java
 * @ide : IntelliJ IDEA
 */
public class ArrayToSqlCollection {
    public static String transform(Object[] list) {
        // 遍历数组
        StringBuilder str = new StringBuilder();
        str.append("(");
        for (int i = 0; i < list.length; ++i) {
            if (i != list.length - 1)
                str.append("'").append(list[i]).append("'").append(",");
            else
                str.append("'").append(list[i]).append("'");
        }
        str.append(")");
        return new String(str);
    }
}
