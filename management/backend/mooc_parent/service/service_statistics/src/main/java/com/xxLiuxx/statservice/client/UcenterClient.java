package com.xxLiuxx.statservice.client;

import com.xxLiuxx.commonutils.entity.CommonResult;
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

  @GetMapping("/ucenterservice/ucenter-member/countRegister/{date}")
  public CommonResult countDailyRegister(@PathVariable String date);
}
