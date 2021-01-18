package com.xxLiuxx.servicebase.handler;

import com.xxLiuxx.commonutils.entity.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Yuchen Liu
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e) {
        e.printStackTrace();
        return CommonResult.error().message("global exception handler");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public CommonResult error(MyException e) {
        e.printStackTrace();
        return CommonResult.error().code(e.getCode()).message(e.getMsg());
    }
}
