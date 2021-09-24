package com.xxLiuxx.ucenterservice.mapper;

import com.xxLiuxx.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Member Mapper interface
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-09
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer countDailyRegister(String date);

}
