package com.xxLiuxx.cmsservice.controller;

import com.xxLiuxx.cmsservice.entity.CrmBanner;
import com.xxLiuxx.cmsservice.service.CrmBannerService;
import com.xxLiuxx.commonutils.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * banner front controller
 * </p>
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("/educms/bannerFront")
@CrossOrigin
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("queryAllBanner")
    public CommonResult queryAllBanner() {
        List<CrmBanner> allBanner = this.bannerService.queryAllBanner();
        return CommonResult.success().data("bannerList", allBanner);
    }
}
