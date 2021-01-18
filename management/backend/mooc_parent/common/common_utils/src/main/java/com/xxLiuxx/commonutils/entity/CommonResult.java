package com.xxLiuxx.commonutils.entity;

import com.xxLiuxx.commonutils.utils.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuchen Liu
 */
@ApiModel("backend common result")
public class CommonResult {

    @ApiModelProperty(value = "status code")
    public int code;

    @ApiModelProperty(value = "message")
    public String message;

    @ApiModelProperty(value = "data")
    public Map<String, Object> data = new HashMap<>();


    /**
     * use private constructor to prevent others from changing the utils
     */
    private CommonResult() {
    }


    public static CommonResult success() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }


    public static CommonResult error() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        return result;
    }


    public static CommonResult notFound() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.NOT_FOUND.getCode());
        result.setMessage(ResultCode.NOT_FOUND.getMessage());
        return result;
    }


    public static CommonResult unauthorized() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.UNAUTHORIZED.getCode());
        result.setMessage(ResultCode.UNAUTHORIZED.getMessage());
        return result;
    }


    /**
     * return the current instance so that the chain programming is allowed
     *
     * @param code
     * @return
     */
    public CommonResult code(int code) {
        this.setCode(code);
        return this;
    }

    public CommonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public CommonResult data(Map<String, Object> data) {
        this.setData(data);
        return this;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
