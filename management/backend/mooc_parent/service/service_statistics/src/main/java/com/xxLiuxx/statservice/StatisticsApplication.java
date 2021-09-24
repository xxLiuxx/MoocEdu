package com.xxLiuxx.statservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yuchen Liu
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.xxLiuxx"})
@MapperScan("com.xxLiuxx.statservice.mapper")
public class StatisticsApplication {

  public static void main(String[] args) {
    SpringApplication.run(StatisticsApplication.class);
  }
}
