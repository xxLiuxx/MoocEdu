package com.xxLiuxx.eduservice.client;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.client.impl.OrderClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yuchen Liu
 */
@FeignClient(name = "service-order", fallback = OrderClientFallback.class)
@Component
public interface OrderClient {

  /**
   * Check if the user has bought the course
   * @param courseId
   * @param memberId
   * @return
   */
  @GetMapping("/orderservice/order/checkCourseStatus/{courseId}/{memberId}")
  Boolean checkCourseStatus(@PathVariable String courseId, @PathVariable String memberId);
}


