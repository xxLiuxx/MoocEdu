package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * course video service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideoByCourseId(String courseId);

    void removeVideo(String id);
}
