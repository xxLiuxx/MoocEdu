package com.xxLiuxx.vodservice.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */

@Component
public class VodConfig implements InitializingBean {
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKey;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;

    public static String ACCESS_KEY;
    public static String ACCESS_SECRET;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = this.accessKey;
        ACCESS_SECRET = this.secretKey;
    }
}
