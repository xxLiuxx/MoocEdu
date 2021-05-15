package com.xxLiuxx.orderservice.service;

import com.xxLiuxx.orderservice.entity.EduOrder;
import com.xxLiuxx.orderservice.entity.EduPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * payment log service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
public interface EduPayLogService extends IService<EduPayLog> {

    Map<String, Object> createCode(String orderNo);

    Map<String, String> checkStatus(String orderNo);

    void updateOrderStatus(Map<String, String> result);
}
