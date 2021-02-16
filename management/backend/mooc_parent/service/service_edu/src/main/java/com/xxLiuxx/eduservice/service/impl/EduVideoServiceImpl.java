package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxLiuxx.eduservice.client.VodClient;
import com.xxLiuxx.eduservice.entity.EduVideo;
import com.xxLiuxx.eduservice.mapper.EduVideoMapper;
import com.xxLiuxx.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * course video service implementation
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void deleteVideoByCourseId(String courseId) {
        // delete the videos on aliyun
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        // get all the videos under the course
        List<EduVideo> list = this.list(wrapperVideo);
        List<String> videoIdList = new ArrayList<>();
        for (EduVideo video : list) {
            String videoSourceId = video.getVideoSourceId();
            if (StringUtils.isNotEmpty(videoSourceId)) {
                videoIdList.add(videoSourceId);
            }
        }

        if (videoIdList.size() > 0) {
            this.vodClient.deleteBatch(videoIdList);
        }

        // delete the video in database
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        this.remove(wrapper);
    }

    @Override
    public void removeVideo(String id) {
        EduVideo video = this.getById(id);
        String videoSourceId = video.getVideoSourceId();

        if (StringUtils.isNotEmpty(videoSourceId)) {
            this.vodClient.deleteVideo(videoSourceId);
        }

        this.removeById(id);
    }
}
