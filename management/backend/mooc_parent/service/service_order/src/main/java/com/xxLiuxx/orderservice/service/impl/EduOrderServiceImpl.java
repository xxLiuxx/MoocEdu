package com.xxLiuxx.orderservice.service.impl;

import com.xxLiuxx.commonutils.orderVo.CourseWebVoOrder;
import com.xxLiuxx.commonutils.orderVo.UcenterMemberOrder;
import com.xxLiuxx.orderservice.client.EduClient;
import com.xxLiuxx.orderservice.client.UcenterClient;
import com.xxLiuxx.orderservice.entity.EduOrder;
import com.xxLiuxx.orderservice.mapper.EduOrderMapper;
import com.xxLiuxx.orderservice.service.EduOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * order 服务实现类
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@Service
public class EduOrderServiceImpl extends ServiceImpl<EduOrderMapper, EduOrder> implements EduOrderService {
    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        // get course info and member info with feign
        CourseWebVoOrder course = eduClient.getCourseInfoForOrder(courseId);
        UcenterMemberOrder member = ucenterClient.getUserInfoById(memberId);

        EduOrder eduOrder = new EduOrder();
        // snowflake is a better solution
        eduOrder.setOrderNo(UUID.randomUUID().toString().replaceAll("-",""));
        eduOrder.setCourseCover(course.getCover());
        eduOrder.setCourseId(courseId);
        eduOrder.setCourseTitle(course.getTitle());
        eduOrder.setMemberId(memberId);
        eduOrder.setMobile(member.getMobile());
        eduOrder.setNickname(member.getNickname());
        eduOrder.setTeacherName(course.getTeacherName());
        eduOrder.setStatus(0);
        eduOrder.setPayType(1);

        this.baseMapper.insert(eduOrder);

        return eduOrder.getOrderNo();
    }
}
