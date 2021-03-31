package com.xxLiuxx.eduservice.client.impl;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.client.UcenterClient;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Yuchen Liu
 */
@Component
public class UcenterClientFallback implements UcenterClient {
    @Override
    public CommonResult getUserInfo(HttpServletRequest request) {
        return CommonResult.error().message("fail to get user information from token");
    }
}
