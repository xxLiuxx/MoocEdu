package com.xxLiuxx.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.commonutils.utils.JwtUtils;
import com.xxLiuxx.commonutils.utils.MD5;
import com.xxLiuxx.servicebase.handler.MyException;
import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.xxLiuxx.ucenterservice.mapper.UcenterMemberMapper;
import com.xxLiuxx.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public String login(UcenterMember member) {
        // get password and mobile
        String mobile = member.getMobile();
        String password = member.getPassword();

        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new MyException(500, "login failed");
        }

        // check the mobile and phone
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        wrapper.eq("password", MD5.encrypt(password));
        UcenterMember mobileMember = this.baseMapper.selectOne(wrapper);

        if(mobileMember == null) {
            throw new MyException(404, "member not found");
        }

        // check if disabled
        if(mobileMember.getIsDisabled()) {
            throw new MyException(500, "member is disabled");
        }

        // generate token
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return token;
    }
}
