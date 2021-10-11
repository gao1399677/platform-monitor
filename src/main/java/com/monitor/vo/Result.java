package com.monitor.vo;

import com.monitor.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private Integer code; //状态码
    private String msg;  //返回信息
    private T data;  //返回数据
    private Integer total; //总条数

    private Result() {

    }


    public static Result success() {
        Result result = new Result();
        result.code = ResultEnum.SUCCESS.getCode();
        result.msg = ResultEnum.SUCCESS.getMsg();
        return result;
    }


    public static Result failed() {
        Result result = new Result();
        result.code = ResultEnum.FAILED.getCode();
        result.msg = ResultEnum.FAILED.getMsg();
        return result;
    }


    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result data(T data) {
        this.data = data;
        return this;
    }

    public Result total(Integer total) {
        this.total = total;
        return this;
    }
}