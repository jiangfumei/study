package com.xy.goone.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fumei.jiang
 * @date 2019-08-06 13:56
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 结果对象
     */
    private Object result;


}
