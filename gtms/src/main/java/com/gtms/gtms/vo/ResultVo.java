package com.gtms.gtms.vo;

import lombok.Data;

/**
 * @Author: 84644
 * @Date: 2019/4/3 14:13
 * @Description:
 **/
@Data
public class ResultVo {
    private Integer code;
    private Object data;
    private String msg;

    public ResultVo(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public ResultVo(Object data) {
        this.data = data;
    }
}
