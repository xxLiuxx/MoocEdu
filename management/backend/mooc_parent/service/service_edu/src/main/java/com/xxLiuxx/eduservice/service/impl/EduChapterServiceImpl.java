package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.EduChapter;
import com.xxLiuxx.eduservice.entity.EduVideo;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;
import com.xxLiuxx.eduservice.entity.bo.VideoBo;
import com.xxLiuxx.eduservice.mapper.EduChapterMapper;
import com.xxLiuxx.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.eduservice.service.EduVideoService;
import com.xxLiuxx.servicebase.handler.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * chapter service implement
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterBo> getChapterVideo(String courseId) {
        // find all chapters
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        chapterQueryWrapper.orderByAsc("sort");
        List<EduChapter> chapterList = this.list(chapterQueryWrapper);

        // find all videos
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        videoQueryWrapper.orderByAsc("sort");
        List<EduVideo> videoList = this.videoService.list(videoQueryWrapper);

        // convert chapters to chapterBo
        List<ChapterBo> chapterBoList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter chapter = chapterList.get(i);
            ChapterBo chapterBo = new ChapterBo();
            BeanUtils.copyProperties(chapter, chapterBo);

            chapterBoList.add(chapterBo);

            // find the videos under each chapter
            List<VideoBo> videoBoList = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo video = videoList.get(j);
                if (StringUtils.equals(chapter.getId(), video.getChapterId())) {
                    VideoBo videoBo = new VideoBo();
                    BeanUtils.copyProperties(video, videoBo);
                    videoBoList.add(videoBo);
                }
            }
            chapterBo.setVideoList(videoBoList);
        }
        return chapterBoList;
    }

    /**
     * if the chapter has videos, it can not be deleted
     *
     * @param chapterId
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", chapterId);
        int count = this.videoService.count(queryWrapper);

        if (count != 0) {
            throw new MyException(500, "The chapter can't be deleted");
        }

        int result = this.baseMapper.deleteById(chapterId);

        return result > 0;
    }

    @Override
    public void deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        this.remove(wrapper);
    }
}
