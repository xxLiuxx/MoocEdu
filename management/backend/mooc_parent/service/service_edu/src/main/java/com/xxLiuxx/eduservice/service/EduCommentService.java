package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * comment service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-03-31
 */
public interface EduCommentService extends IService<EduComment> {

    /**
     * get all comments with pagination
     * @param page
     * @param limit
     * @return
     */
    PageResult<EduComment> getAllComment(Long page, Long limit);

    /**
     * add comment
     * @param comment
     * @param request
     * @return
     */
    int addComment(EduComment comment, HttpServletRequest request);
}
