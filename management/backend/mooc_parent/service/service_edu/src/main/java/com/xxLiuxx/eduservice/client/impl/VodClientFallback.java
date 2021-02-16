package com.xxLiuxx.eduservice.client.impl;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yuchen Liu
 */

@Component
public class VodClientFallback implements VodClient {
    @Override
    public CommonResult deleteVideo(String videoId) {
        return CommonResult.error().message("delete video fails");
    }

    @Override
    public CommonResult deleteBatch(List<String> videoIdList) {
        return CommonResult.error().message("delete batch videos fails");
    }
}
