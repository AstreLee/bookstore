package com.xin.bookstore.utils;

import java.util.UUID;

/**
 * @author : joylxer
 * @date : 2022/11/25 - 10:47
 * @file : GetUUID.java
 * @ide : IntelliJ IDEA
 */
public class GetUUID {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
