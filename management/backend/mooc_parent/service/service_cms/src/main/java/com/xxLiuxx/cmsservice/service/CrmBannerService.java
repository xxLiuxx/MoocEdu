package com.xxLiuxx.cmsservice.service;

import com.xxLiuxx.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxLiuxx.commonutils.entity.PageResult;

import java.util.List;

/**
 * <p>
 * banner service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-02-20
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * query banner with pagination
     * @param pageNumber
     * @param limit
     * @return
     */
    PageResult<CrmBanner> queryBanner(Long pageNumber, Long limit);

    List<CrmBanner> queryAllBanner();
}
