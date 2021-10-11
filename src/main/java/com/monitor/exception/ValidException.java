package com.monitor.exception;

public class ValidException extends Exception {
    private static final long serialVersionUID = -5281976357295182021L;
    private Integer code;

    public ValidException() {
    }

    public ValidException(String msg) {
        super(msg);
        this.code = 0;
    }

    public ValidException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public Throwable fillInStackTrace() {
        return this;
    }
}