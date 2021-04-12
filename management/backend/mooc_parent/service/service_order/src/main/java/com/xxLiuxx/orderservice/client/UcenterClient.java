package com.xxLiuxx.orderservice.client;

import com.xxLiuxx.commonutils.orderVo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yuchen Liu
 */
@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/ucenterservice/ucenter-member/{memberId}")
    UcenterMemberOrder getUserInfoById(@PathVariable String memberId);
}
