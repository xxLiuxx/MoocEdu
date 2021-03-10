package com.xxLiuxx.msmservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Yuchen Liu
 */
@Service
public interface MsmService {
    boolean sendVerifyCode(Map<String, Object> param, String phone);
}
