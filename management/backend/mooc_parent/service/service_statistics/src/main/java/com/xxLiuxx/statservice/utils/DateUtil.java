package com.xxLiuxx.statservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date operation util class
 *
 * @author qy
 * @since 1.0
 */
public class DateUtil {

  private static final String dateFormat = "yyyy-MM-dd";

  /**
   * format date
   *
   * @param date
   * @return
   */
  public static String formatDate(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(date);

  }

  /**
   * add x days to specified date
   *
   * @param date   specified date non-null
   * @param amount amount of days to be added, can be negative
   */
  public static Date addDays(Date date, int amount) {
    Calendar now = Calendar.getInstance();
    now.setTime(date);
    now.set(Calendar.DATE, now.get(Calendar.DATE) + amount);
    return now.getTime();
  }
}
