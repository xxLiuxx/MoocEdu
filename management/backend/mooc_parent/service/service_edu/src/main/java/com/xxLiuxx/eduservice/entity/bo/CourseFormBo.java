package com.xxLiuxx.eduservice.entity.bo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Yuchen Liu
 */
public class CourseFormBo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "course id")
    private String id;

    @ApiModelProperty(value = "teacher id")
    private String teacherId;

    @ApiModelProperty(value = "subject id")
    private String subjectId;

    @ApiModelProperty(value = "subject parent id")
    private String subjectParentId;

    @ApiModelProperty(value = "course title")
    private String title;

    @ApiModelProperty(value = "price, 0: free")
    private BigDecimal price;

    @ApiModelProperty(value = "course time")
    private Integer lessonNum;

    @ApiModelProperty(value = "cover")
    private String cover;

    @ApiModelProperty(value = "course description")
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }
}
