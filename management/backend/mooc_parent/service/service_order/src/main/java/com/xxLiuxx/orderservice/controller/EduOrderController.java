package com.xxLiuxx.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.utils.JwtUtils;
import com.xxLiuxx.orderservice.entity.EduOrder;
import com.xxLiuxx.orderservice.service.EduOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * order controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/orderservice/order")
@CrossOrigin
public class EduOrderController {
    @Autowired
    private EduOrderService orderService;

    @PostMapping("createOrder/{courseId}")
    public CommonResult createOrder(@PathVariable String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNumber = this.orderService.createOrder(courseId, memberId);
        return CommonResult.success().data("orderNumber", orderNumber);
    }

    @GetMapping("getOrder/{orderNo}")
    public CommonResult getOrderInfo(@PathVariable String orderNo) {
        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        EduOrder order = this.orderService.getOne(wrapper);

        return CommonResult.success().data("order", order);
    }
}

