package com.xxLiuxx.eduservice.entity.bo;

/**
 * @author Yuchen Liu
 */
public class VideoBo {
    private String id;

    private String title;

    private String videoSourceId;

    public String getVideoSourceId() {
        return videoSourceId;
    }

    public void setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
    }

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
}
