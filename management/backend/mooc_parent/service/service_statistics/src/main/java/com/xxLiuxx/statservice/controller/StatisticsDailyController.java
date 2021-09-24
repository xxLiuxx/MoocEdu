package com.xxLiuxx.statservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.statservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * daily statistics controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/statservice/statistics-daily")
public class StatisticsDailyController {

  @Autowired
  private StatisticsDailyService statisticsDailyService;

  @PostMapping("countRegister/{date}")
  public CommonResult countRegister(@PathVariable String date) {
    this.statisticsDailyService.countRegister(date);
    return CommonResult.success();
  }
}

