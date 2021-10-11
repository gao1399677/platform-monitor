package com.monitor.enums;

public enum ResultEnum {
    // 可自己添加常用状态
    SUCCESS(1, "成功"),
    FAILED(0, "失败");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}