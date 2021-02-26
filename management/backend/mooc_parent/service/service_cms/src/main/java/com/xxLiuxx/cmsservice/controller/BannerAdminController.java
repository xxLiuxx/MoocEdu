package com.xxLiuxx.cmsservice.controller;


import com.xxLiuxx.cmsservice.entity.CrmBanner;
import com.xxLiuxx.cmsservice.service.CrmBannerService;
import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * banner admin controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-02-20
 */
@RestController
@RequestMapping("/educms/bannerAdmin")
@CrossOrigin
@Api(tags = {"banner controller"})
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("queryBanner/{pageNumber}/{limit}")
    @ApiOperation("query banner with pagination")
    public CommonResult queryBanner(@PathVariable("pageNumber") Long pageNumber, @PathVariable("limit") Long limit) {
        PageResult<CrmBanner> pageResult = this.bannerService.queryBanner(pageNumber, limit);
        List<CrmBanner> bannerList = pageResult.getItems();

        if (CollectionUtils.isEmpty(bannerList)) {
            return CommonResult.notFound().message("banner not found");
        }
        return CommonResult.success().data("bannerList", bannerList).data("total", pageResult.getTotalRows());
    }

    @GetMapping("queryBanner/{id}")
    @ApiOperation("query banner with id")
    public CommonResult queryBannerById(@PathVariable String id) {
        CrmBanner banner = this.bannerService.getById(id);
        return CommonResult.success().data("banner", banner);
    }

    @PostMapping("addBanner")
    @ApiOperation("add banner")
    public CommonResult addBanner(@RequestBody CrmBanner banner) {
        this.bannerService.save(banner);
        return CommonResult.success().message("banner added");
    }

    @PutMapping("updateBanner")
    @ApiOperation("update banner")
    public CommonResult updateBanner(@RequestBody CrmBanner banner) {
        this.bannerService.updateById(banner);
        return CommonResult.success().message("banner updated");
    }

    @DeleteMapping("deleteBanner/{id}")
    @ApiOperation("delete banner by id")
    public CommonResult deleteBanner(@PathVariable String id) {
        this.bannerService.removeById(id);
        return CommonResult.success().message("banner deleted");
    }
}

