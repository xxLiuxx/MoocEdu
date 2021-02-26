package com.xxLiuxx.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxLiuxx.cmsservice.entity.CrmBanner;
import com.xxLiuxx.cmsservice.mapper.CrmBannerMapper;
import com.xxLiuxx.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.commonutils.entity.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * banner service implement
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-02-20
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public PageResult<CrmBanner> queryBanner(Long pageNumber, Long limit) {
        Page<CrmBanner> bannerPage = new Page<>(pageNumber, limit);
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("gmt_modified");
        this.page(bannerPage, wrapper);

        return new PageResult<>(bannerPage.getCurrent(), bannerPage.getTotal(), bannerPage.getRecords());
    }

    @Override
    public List<CrmBanner> queryAllBanner() {
        // desc order by create time
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 2");

        List<CrmBanner> allBanner = this.baseMapper.selectList(wrapper);
        return allBanner;
    }
}
