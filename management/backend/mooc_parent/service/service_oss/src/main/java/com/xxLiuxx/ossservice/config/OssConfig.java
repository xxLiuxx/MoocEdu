package com.xxLiuxx.ossservice.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class OssConfig implements InitializingBean {
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessKey;

    @Value("${spring.cloud.alicloud.secret-key}")
    private String accessSecret;

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;

    public static String ACCESS_KEY;
    public static String ACCESS_SECRET;
    public static String ENDPOINT;
    public static String BUCKET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = this.accessKey;
        ACCESS_SECRET = this.accessSecret;
        ENDPOINT = this.endpoint;
        BUCKET = this.bucket;
    }
}
