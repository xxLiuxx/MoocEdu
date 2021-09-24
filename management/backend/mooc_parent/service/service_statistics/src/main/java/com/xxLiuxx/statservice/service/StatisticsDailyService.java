package com.xxLiuxx.statservice.service;

import com.xxLiuxx.statservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * daily statistics service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-08-29
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

  void countRegister(String date);
}
