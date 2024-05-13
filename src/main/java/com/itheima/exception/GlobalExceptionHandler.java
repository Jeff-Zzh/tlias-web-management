package com.itheima.exception;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) // 捕获所有异常
    public Result ex(Exception ex) {
        ex.printStackTrace(); // 显示异常堆栈信息
        return Result.error("对不起，操作失败，请联系管理员"+ex.getMessage());
    }
}
