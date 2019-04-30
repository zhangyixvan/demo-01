package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessResult<E> {
    /**
     * code为0表示成功
     * 1:未登录
     * 2:已登录
     * 3:无权限
     */
    private ResultCode code;
    private String msg;
    private E data;

    public BusinessResult(ResultCode code, String msg, E e) {
        this.code = code;
        this.msg = msg;
        this.data = e;
    }

    public enum ResultCode {
        success,
        notLogin,
        isLogon,
        userNotFound,
        logError,
        notPermission,
        system_error
    }

}

