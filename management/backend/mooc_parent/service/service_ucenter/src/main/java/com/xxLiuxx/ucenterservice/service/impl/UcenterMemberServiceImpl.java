package com.xxLiuxx.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.commonutils.utils.JwtUtils;
import com.xxLiuxx.commonutils.utils.MD5;
import com.xxLiuxx.servicebase.handler.MyException;
import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.xxLiuxx.ucenterservice.entity.vo.RegisterVo;
import com.xxLiuxx.ucenterservice.mapper.UcenterMemberMapper;
import com.xxLiuxx.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * member service implement
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-09
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String login(UcenterMember member) {
        // get password and mobile
        String mobile = member.getMobile();
        String password = member.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new MyException(500, "login failed");
        }

        // check the mobile and phone
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        wrapper.eq("password", MD5.encrypt(password));
        UcenterMember mobileMember = this.baseMapper.selectOne(wrapper);

        if (mobileMember == null) {
            throw new MyException(404, "member not found");
        }

        // check if disabled
        if (mobileMember.getIsDisabled()) {
            throw new MyException(500, "member is disabled");
        }

        // generate token
        return JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if (StringUtils.isAnyEmpty(nickname, mobile, password, code)) {
            throw new MyException(500, "fail to register");
        }

        // verify code
        if (!StringUtils.equals(code, this.redisTemplate.opsForValue().get(mobile))) {
            throw new MyException(500, "wrong verify code");
        }

        // check duplicate
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count != 0) {
            throw new MyException(500, "mobile exists");
        }

        // add to database
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("");

        this.baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getByOpenId(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        UcenterMember member = this.baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Map<String, Object> getUserInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        // get id from token and search member by id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = this.baseMapper.selectById(memberId);

        map.put("memberId", memberId);
        map.put("avatar", member.getAvatar());
        map.put("nickname", member.getNickname());

        return map;

    }

}
