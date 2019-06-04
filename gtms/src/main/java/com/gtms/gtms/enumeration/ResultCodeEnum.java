package com.gtms.gtms.enumeration;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    FAIL(2,"失败"),SUCCESS(1,"成功");
    private Integer code;
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
