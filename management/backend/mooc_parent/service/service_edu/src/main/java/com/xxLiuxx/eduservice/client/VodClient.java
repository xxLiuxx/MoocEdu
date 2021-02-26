package com.xxLiuxx.eduservice.client;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.client.impl.VodClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Yuchen Liu
 */

@FeignClient(name = "service-vod", fallback = VodClientFallback.class)
@Component
public interface VodClient {

    /**
     * delete video in Aliyun
     * @param videoId
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteVideo/{videoId}")
    CommonResult deleteVideo(@PathVariable("videoId") String videoId);

    /**
     * delete multiple videos in Aliyun
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduvod/video/deleteBatch")
    CommonResult deleteBatch(@RequestParam List<String> videoIdList);
}
