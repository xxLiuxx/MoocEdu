package com.xxLiuxx.orderservice.client;

import com.xxLiuxx.commonutils.orderVo.CourseWebVoOrder;
import com.xxLiuxx.orderservice.client.impl.EduClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yuchen Liu
 */
@Component
@FeignClient(name = "service-edu", fallback = EduClientFallback.class)
public interface EduClient {

    @GetMapping("/eduservice/front/course/getCourseInfoForOrder/{courseId}")
    CourseWebVoOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId);
}

