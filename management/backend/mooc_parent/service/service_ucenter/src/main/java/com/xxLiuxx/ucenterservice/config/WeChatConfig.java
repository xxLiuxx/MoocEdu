package com.xxLiuxx.ucenterservice.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuchen Liu
 */
@Component
public class WeChatConfig implements InitializingBean {
    @Value("${wx.open.appId}")
    public String appId;

    @Value("${wx.open.appSecret}")
    public String appSecret;

    @Value("${wx.open.redirectUrl}")
    public String redirectUrl;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        REDIRECT_URL = redirectUrl;
    }
}
