package com.xxLiuxx.orderservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.orderservice.entity.EduOrder;
import com.xxLiuxx.orderservice.service.EduPayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * payment log controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/orderservice/paylog")
@CrossOrigin
@Api(tags = {"WeChat Pay Controller"})
public class EduPayLogController {
    @Autowired
    private EduPayLogService payLogService;

    @GetMapping("createCode")
    @ApiOperation(value = "create WeChat Pay QR Code")
    public CommonResult createCode(@RequestBody EduOrder order) {
        Map<String, Object> payInfo = this.payLogService.createCode(order);

        return CommonResult.success().data("payInfo", payInfo);
    }

    @GetMapping("checkStatus/{orderNo}")
    @ApiOperation(value = "check payment status")
    public CommonResult checkStatus(@PathVariable("orderNo") String orderNo) {
        Map<String, String> result = this.payLogService.checkStatus(orderNo);

        if(result == null) {
            return CommonResult.error().message("payment failure");
        }

        if("SUCCESS".equals(result.get("trade_state"))) {
            // update record
            this.payLogService.updateOrderStatus(result);
            return CommonResult.success().message("payment success");
        }

        return CommonResult.success().message("payment in progress");
    }

}

