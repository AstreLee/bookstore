package com.xin.bookstore.entity.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 18:31
 * @file : ResultVO.java
 * @ide : IntelliJ IDEA
 */
@Data
public class ResultVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private Object data;

    public ResultVO() {}

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ResultVO(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}

