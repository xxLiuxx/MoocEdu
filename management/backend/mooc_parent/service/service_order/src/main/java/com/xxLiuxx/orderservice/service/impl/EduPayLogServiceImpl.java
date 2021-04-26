package com.xxLiuxx.orderservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.xxLiuxx.orderservice.entity.EduOrder;
import com.xxLiuxx.orderservice.entity.EduPayLog;
import com.xxLiuxx.orderservice.mapper.EduPayLogMapper;
import com.xxLiuxx.orderservice.service.EduOrderService;
import com.xxLiuxx.orderservice.service.EduPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.orderservice.utils.HttpClient;
import com.xxLiuxx.servicebase.handler.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * payment log service implment
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@Service
public class EduPayLogServiceImpl extends ServiceImpl<EduPayLogMapper, EduPayLog> implements EduPayLogService {

    @Autowired
    private EduOrderService orderService;

    @Override
    public Map<String, Object> createCode(EduOrder order) {
        try {
            String orderNo = order.getOrderNo();

            // use map to set wechat pay params
            Map<String, String> requestInfo = new HashMap<>();
            requestInfo.put("appid", "wx74862e0dfcf69954");
            requestInfo.put("mch_id", "1558950191");
            requestInfo.put("nonce_str", WXPayUtil.generateNonceStr());
            requestInfo.put("body", order.getCourseTitle());
            requestInfo.put("out_trade_no", orderNo);
            requestInfo.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
            requestInfo.put("spbill_create_ip", "127.0.0.1");
            requestInfo.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            requestInfo.put("trade_type", "NATIVE");

            // httpclient request, xml format
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            httpClient.setXmlParam(WXPayUtil.generateSignedXml(requestInfo,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            httpClient.setHttps(true);
            httpClient.post();

            // get response
            String content = httpClient.getContent();
            Map<String,String > resultMap = WXPayUtil.xmlToMap(content);

            Map<String, Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));
            return map;
        } catch (Exception e) {
            throw new MyException(500, "fail to generate pay code");
        }

    }

    @Override
    public Map<String, String> checkStatus(String orderNo) {
        try{
            Map<String, String> m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateOrderStatus(Map<String, String> result) {
        String orderNo = result.get("out_trade_no");

        QueryWrapper<EduOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        EduOrder order = this.orderService.getOne(wrapper);

        if(order.getStatus() == 1) {return;}
        order.setStatus(1);
        this.orderService.updateById(order);

        EduPayLog payLog = new EduPayLog();
        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        payLog.setPayType(1);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(result.get("trade_state"));
        payLog.setTransactionId(result.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(result));

        baseMapper.insert(payLog);
    }
}
