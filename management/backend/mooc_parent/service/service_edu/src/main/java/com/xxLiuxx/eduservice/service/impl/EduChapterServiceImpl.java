package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.eduservice.entity.EduChapter;
import com.xxLiuxx.eduservice.entity.EduVideo;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;
import com.xxLiuxx.eduservice.entity.bo.VideoBo;
import com.xxLiuxx.eduservice.mapper.EduChapterMapper;
import com.xxLiuxx.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.eduservice.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<EduChapter> chapterList = this.list(chapterQueryWrapper);

        // find all videos
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
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
}
