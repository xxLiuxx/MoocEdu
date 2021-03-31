package com.xxLiuxx.eduservice.client;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.client.impl.UcenterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Yuchen Liu
 */
@FeignClient(name = "service-ucenter", fallback = UcenterClientFallback.class)
@Component
public interface UcenterClient {

    @GetMapping("/ucenterservice/ucenter-member/getUserInfo")
     CommonResult getUserInfo(HttpServletRequest request);
}
