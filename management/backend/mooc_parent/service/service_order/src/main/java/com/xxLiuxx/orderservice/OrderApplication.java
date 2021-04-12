package com.xxLiuxx.orderservice;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yuchen Liu
 */

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.xxLiuxx"})
@MapperScan("com.xxLiuxx.orderservice.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
