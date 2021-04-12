package com.xxLiuxx.orderservice.client;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.orderVo.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yuchen Liu
 */
@Component
@FeignClient(name = "serivce-edu")
public interface EduClient {

    @GetMapping("/eduservice/front/course/getCourseInfoForOrder/{courseId}")
    CourseWebVoOrder getCourseInfoForOrder(@PathVariable String courseId);
}
