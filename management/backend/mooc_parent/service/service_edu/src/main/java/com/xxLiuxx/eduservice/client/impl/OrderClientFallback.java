package com.xxLiuxx.eduservice.client.impl;

import com.xxLiuxx.eduservice.client.OrderClient;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class OrderClientFallback implements OrderClient {

  @Override
  public Boolean checkCourseStatus(String courseId, String memberId) {
    return false;
  }
}
