package com.xxLiuxx.eduservice.entity.bo;

import java.util.List;

/**
 * @author Yuchen Liu
 */
public class ChapterBo {
    private String id;

    private String title;

    /**
     * chapter and video have a one-to-many relation
     */
    private List<VideoBo> videoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VideoBo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoBo> videoList) {
        this.videoList = videoList;
    }
}
