package com.xxLiuxx.eduservice.entity.frontVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Yuchen Liu
 */
@ApiModel(value = "course front query", description = "course front query object")
public class CourseFrontVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "course title")
    private String title;

    @ApiModelProperty(value = "teacher id")
    private String teacherId;

    @ApiModelProperty(value = "subject one id")
    private String subjectParentId;

    @ApiModelProperty(value = "subject two id")
    private String subjectId;

    @ApiModelProperty(value = "sort by count")
    private String buyCountSort;

    @ApiModelProperty(value = "sort by time")
    private String gmtCreateSort;

    @ApiModelProperty(value = "sort by price")
    private String priceSort;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getBuyCountSort() {
        return buyCountSort;
    }

    public void setBuyCountSort(String buyCountSort) {
        this.buyCountSort = buyCountSort;
    }

    public String getGmtCreateSort() {
        return gmtCreateSort;
    }

    public void setGmtCreateSort(String gmtCreateSort) {
        this.gmtCreateSort = gmtCreateSort;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }
}

