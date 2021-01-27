package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;

import java.util.List;

/**
 * <p>
 * chapter service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterBo> getChapterVideo(String courseId);
}
