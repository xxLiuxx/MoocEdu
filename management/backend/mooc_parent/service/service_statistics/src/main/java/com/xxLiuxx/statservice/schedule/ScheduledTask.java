package com.xxLiuxx.statservice.schedule;

import static com.xxLiuxx.statservice.utils.DateUtil.addDays;

import com.xxLiuxx.statservice.service.StatisticsDailyService;
import com.xxLiuxx.statservice.utils.DateUtil;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class ScheduledTask {

  private StatisticsDailyService statisticsDailyService;

  /**
   * Get the total number of registration of the previous day
   * The task will be performed on 1 a.m. everyday
   */
  @Scheduled(cron = "0 0 1 * * ?")
  public void countDailyRegisterScheduled() {
    this.statisticsDailyService.countRegister(DateUtil.formatDate(addDays(new Date(), -1)));
  }
}
