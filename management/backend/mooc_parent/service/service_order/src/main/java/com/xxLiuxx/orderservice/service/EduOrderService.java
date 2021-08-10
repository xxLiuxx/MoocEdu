package com.xxLiuxx.orderservice.service;

import com.xxLiuxx.orderservice.entity.EduOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * order service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
public interface EduOrderService extends IService<EduOrder> {

    String createOrder(String courseId, String memberId);

    Boolean checkCourseStatus(String courseId, String memberId);
}
