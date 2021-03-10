package com.xxLiuxx.commonutils.utils;

/**
 * @author Yuchen Liu
 */

public enum ResultCode {
    //common status code
    SUCCESS(200, "success"),
    ERROR(500, "error"),
    NOT_FOUND(404, "not found"),
    UNAUTHORIZED(401, "expired token or not logged in");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
