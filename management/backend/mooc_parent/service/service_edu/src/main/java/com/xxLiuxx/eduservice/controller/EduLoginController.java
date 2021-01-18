package com.xxLiuxx.eduservice.controller;

import com.xxLiuxx.commonutils.entity.CommonResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public CommonResult login() {
        return CommonResult.success().data("token", "admin");
    }

    @GetMapping("info")
    public CommonResult getInfo() {
        return CommonResult.success().data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
