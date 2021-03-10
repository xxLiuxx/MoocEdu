package com.xxLiuxx.ucenterservice.service;

import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * member service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * login
     * @param member
     * @return token
     */
    String login(UcenterMember member);
}
