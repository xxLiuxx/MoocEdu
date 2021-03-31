package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.client.UcenterClient;
import com.xxLiuxx.eduservice.entity.EduComment;
import com.xxLiuxx.eduservice.mapper.EduCommentMapper;
import com.xxLiuxx.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * comment service implementation
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-31
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public PageResult<EduComment> getAllComment(Long page, Long limit) {
        Page<EduComment> commentPage = new Page<>(page, limit);

        this.page(commentPage);

        return new PageResult<>(commentPage.getCurrent(), commentPage.getTotal(), commentPage.getRecords());

    }

    @Override
    public int addComment(EduComment comment, HttpServletRequest request) {
        // courseId and content is in the comment
        // get user information from token
        CommonResult result = this.ucenterClient.getUserInfo(request);
        Map<String, Object> userInfo = (Map<String, Object>) result.data.get("userInfo");

        comment.setAvatar((String) userInfo.get("avatar"));
        comment.setNickname((String) userInfo.get("nickname"));
        comment.setMemberId((String) userInfo.get("memberId"));

        int flag = this.baseMapper.insert(comment);

        return flag;
    }
}
