package com.xxLiuxx.servicebase.handler;

/**
 * @author Yuchen Liu
 */
public class MyException extends RuntimeException{
    public int code;
    public String msg;

    public MyException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MyException() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
