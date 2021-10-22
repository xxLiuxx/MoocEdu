package com.xxLiuxx.statservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.statservice.client.UcenterClient;
import com.xxLiuxx.statservice.entity.StatisticsDaily;
import com.xxLiuxx.statservice.mapper.StatisticsDailyMapper;
import com.xxLiuxx.statservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * daily statistics service impl
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-08-29
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

  @Autowired
  private UcenterClient ucenterClient;

  @Override
  public void countRegister(String date) {
    // call feign client to get the result
    CommonResult result = this.ucenterClient.countDailyRegister(date);
    // decode the data from the result
    Integer dailyRegister = (Integer) result.getData().get("dailyRegister");

    // delete the old data
    QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
    wrapper.eq("data_calculated", date);
    this.baseMapper.delete(wrapper);

    // insert data into database
    StatisticsDaily statisticsDaily = new StatisticsDaily();
    statisticsDaily.setRegisterNum(dailyRegister);
    statisticsDaily.setDateCalculated(date);

    this.baseMapper.insert(statisticsDaily);
  }
}
