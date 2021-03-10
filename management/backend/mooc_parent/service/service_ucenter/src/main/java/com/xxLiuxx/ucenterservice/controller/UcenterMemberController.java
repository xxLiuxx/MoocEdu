package com.xxLiuxx.ucenterservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.xxLiuxx.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * member controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/ucenterservice/ucenter-member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public CommonResult login(@RequestBody UcenterMember member) {
        String token = this.memberService.login(member);
        return CommonResult.success().data("token", token);
    }
}

