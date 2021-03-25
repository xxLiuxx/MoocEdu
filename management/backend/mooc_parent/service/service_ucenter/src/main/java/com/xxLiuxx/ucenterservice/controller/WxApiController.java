package com.xxLiuxx.ucenterservice.controller;

import com.google.gson.Gson;
import com.xxLiuxx.commonutils.utils.HttpClientUtils;
import com.xxLiuxx.commonutils.utils.JwtUtils;
import com.xxLiuxx.servicebase.handler.MyException;
import com.xxLiuxx.ucenterservice.config.WeChatConfig;
import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.xxLiuxx.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author Yuchen Liu
 */
@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
@Api(tags = {"OAuth WeChat login/register controller"})
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;

    @GetMapping("login")
    @ApiOperation(value = "generate QR code")
    public String generateQRCode() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        String redirectUrl = WeChatConfig.REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(
                baseUrl,
                WeChatConfig.APP_ID,
                redirectUrl,
                "mooc"
        );

        return "redirect:" + url;
    }

    @GetMapping("callback")
    @ApiOperation(value = "get user info from QR code")
    public String callback(String code, String state) {
        // get code
        // request WeChat URL with code, get access_token and openId
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";

        String accessTokenUrl = String.format(
                baseAccessTokenUrl,
                WeChatConfig.APP_ID,
                WeChatConfig.APP_SECRET,
                code
        );

        // request url with httpclient, get result
        String accessTokenInfo = null;
        try {
            accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // the return info is a json string, convert it to map
        Gson gson = new Gson();
        HashMap<String, Object> map = gson.fromJson(accessTokenInfo, HashMap.class);
        String accessToken = (String) map.get("access_token");
        String openid = (String) map.get("openid");

        // check if the user is in database
        UcenterMember member = this.memberService.getByOpenId(openid);
        if (member == null) {
            System.out.println("user register");

            // request WeChat server and get the user info
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);

            String resultUserInfo = null;
            try {
                resultUserInfo = HttpClientUtils.get(userInfoUrl);
            } catch (Exception e) {
                throw new MyException(500, "user not found");
            }

            // get user info from json and put it in database
            HashMap<String, Object> userMap = gson.fromJson(resultUserInfo, HashMap.class);
            String nickname = (String) userMap.get("nickname");
            String avatar = (String) userMap.get("headimgurl");

            // save user
            UcenterMember ucenterMember = new UcenterMember();
            ucenterMember.setNickname(nickname);
            ucenterMember.setAvatar(avatar);
            ucenterMember.setOpenid(openid);
            this.memberService.save(ucenterMember);
        }

        // if the user exist, send the info with token
        assert member != null;
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());

        return "redirect:http://localhost:3000?token=" + token;
    }
}
