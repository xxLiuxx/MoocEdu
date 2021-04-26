package com.xxLiuxx.orderservice.client.impl;

import com.xxLiuxx.commonutils.orderVo.UcenterMemberOrder;
import com.xxLiuxx.orderservice.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class UcenterClientFallback implements UcenterClient {
    @Override
    public UcenterMemberOrder getUserInfoById(String memberId) {
        System.out.println("UcenterClient fallback");
        return null;
    }
}
