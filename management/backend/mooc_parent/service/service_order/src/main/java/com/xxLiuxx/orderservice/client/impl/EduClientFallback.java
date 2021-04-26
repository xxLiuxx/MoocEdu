package com.xxLiuxx.orderservice.client.impl;

import com.xxLiuxx.commonutils.orderVo.CourseWebVoOrder;
import com.xxLiuxx.orderservice.client.EduClient;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class EduClientFallback implements EduClient {
    @Override
    public CourseWebVoOrder getCourseInfoForOrder(String courseId) {
        System.out.println("EduClient fallback");
        return null;
    }
}
