package com.xxLiuxx.eduservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * course video
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@ApiModel(value="EduVideo", description="course video")
public class EduVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "video id")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "course id")
    private String courseId;

    @ApiModelProperty(value = "chapter id")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "video source")
    private String videoSourceId;

    @ApiModelProperty(value = "video file name")
    private String videoOriginalName;

    @ApiModelProperty(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "play count")
    private Long playCount;

    @ApiModelProperty(value = "free, 0: not free 1: free")
    private Integer isFree;

    @ApiModelProperty(value = "video length")
    private Float duration;

    @ApiModelProperty(value = "status: Empty Transcoding  Normal")
    private String status;

    @ApiModelProperty(value = "video source file size")
    private Long size;

    @ApiModelProperty(value = "optimistic lock")
    private Long version;

    @ApiModelProperty(value = "create time")
    private Date gmtCreate;

    @ApiModelProperty(value = "update time")
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoSourceId() {
        return videoSourceId;
    }

    public void setVideoSourceId(String videoSourceId) {
        this.videoSourceId = videoSourceId;
    }

    public String getVideoOriginalName() {
        return videoOriginalName;
    }

    public void setVideoOriginalName(String videoOriginalName) {
        this.videoOriginalName = videoOriginalName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "EduVideo{" +
        "id=" + id +
        ", courseId=" + courseId +
        ", chapterId=" + chapterId +
        ", title=" + title +
        ", videoSourceId=" + videoSourceId +
        ", videoOriginalName=" + videoOriginalName +
        ", sort=" + sort +
        ", playCount=" + playCount +
        ", isFree=" + isFree +
        ", duration=" + duration +
        ", status=" + status +
        ", size=" + size +
        ", version=" + version +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
