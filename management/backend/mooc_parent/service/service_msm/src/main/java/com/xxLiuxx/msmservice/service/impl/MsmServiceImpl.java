package com.xxLiuxx.msmservice.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xxLiuxx.msmservice.service.MsmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Yuchen Liu
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean sendVerifyCode(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAIq6nIPY09VROj", "FQ7UcixT9wEqMv9F35nORPqKr8XkTF");
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(phone);
        request.setSignName("Mooc Edu");
        request.setTemplateCode("SMS_133976814");
        request.setTemplateParam("{\"code\":\"" + param.get("code") + "\"}");

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = client.getAcsResponse(request);
            Object object;
            boolean success = sendSmsResponse != null && sendSmsResponse.getCode().equals("OK");
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
