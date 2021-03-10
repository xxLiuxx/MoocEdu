package com.xxLiuxx.msmservice.controller;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.utils.NumberUtils;
import com.xxLiuxx.msmservice.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/send/{phone}")
    public CommonResult sendVerifyCode(@PathVariable String phone) {
        // get verify code from redis
        String code = redisTemplate.opsForValue().get("phone");
        if (StringUtils.isNotEmpty(code)) {
            return CommonResult.success();
        }
        // if redis doesn't have the code, generate one
        code = NumberUtils.generateCode(6);
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        boolean isSend = this.msmService.sendVerifyCode(param, phone);
        if(!isSend) {
            return CommonResult.error().message("fail to send code");
        }


        redisTemplate.opsForValue().set("phone", code, 5, TimeUnit.MINUTES);
        return CommonResult.success();
    }
}
