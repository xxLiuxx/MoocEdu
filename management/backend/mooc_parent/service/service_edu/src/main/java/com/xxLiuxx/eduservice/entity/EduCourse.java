package com.xxLiuxx.eduservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * course
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@ApiModel(value = "EduCourse", description = "course")
public class EduCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "course id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "teacher id")
    private String teacherId;

    @ApiModelProperty(value = "subject id")
    private String subjectId;

    @ApiModelProperty(value = "subject parent id")
    private String subjectParentId;

    @ApiModelProperty(value = "title")
    private String title;

    @ApiModelProperty(value = "price，0: free course")
    private BigDecimal price;

    @ApiModelProperty(value = "total course time")
    private Integer lessonNum;

    @ApiModelProperty(value = "cover")
    private String cover;

    @ApiModelProperty(value = "sales")
    private Long buyCount;

    @ApiModelProperty(value = "views")
    private Long viewCount;

    @ApiModelProperty(value = "optimistic lock")
    private Long version;

    @ApiModelProperty(value = "status Draft: not published  Normal: published")
    private String status;

    @ApiModelProperty(value = "logic delete 1（true）deleted， 0（false）not deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "create time")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "update time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


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

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
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

    public Long getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Long buyCount) {
        this.buyCount = buyCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
        return "EduCourse{" +
                "id=" + id +
                ", teacherId=" + teacherId +
                ", subjectId=" + subjectId +
                ", subjectParentId=" + subjectParentId +
                ", title=" + title +
                ", price=" + price +
                ", lessonNum=" + lessonNum +
                ", cover=" + cover +
                ", buyCount=" + buyCount +
                ", viewCount=" + viewCount +
                ", version=" + version +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
