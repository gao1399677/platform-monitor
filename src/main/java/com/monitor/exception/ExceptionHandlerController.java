package com.monitor.exception;

import com.monitor.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(ValidException.class)
    public Result ytExceptionHandler(ValidException e) {
        return Result.failed().code(e.getCode()).msg(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        log.info("RuntimeException{}", e);
        log.error("RuntimeException{}", e);
        String message = "系统错误";
        return Result.failed().msg(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        log.info("MethodArgumentNotValidException{}", e);
        log.error("MethodArgumentNotValidException{}", e);
        String[] msg = {"操作失败"};
        e.getBindingResult().getFieldErrors().forEach(error -> {
            msg[0] = msg[0].concat(",").concat(error.getDefaultMessage());
        });
        return Result.failed().msg(msg[0]);
    }
}
