package com.xxLiuxx.eduservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * Teacher
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-12
 */
@ApiModel(value="EduTeacher", description="Teacher")
public class EduTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Teacher ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "Teacher Name")
    private String name;

    @ApiModelProperty(value = "Teacher Description")
    private String intro;

    @ApiModelProperty(value = "Teacher Career Description")
    private String career;

    @ApiModelProperty(value = "Teacher Level")
    private Integer level;

    @ApiModelProperty(value = "Teacher Avatar")
    private String avatar;

    @ApiModelProperty(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "Logic Delete")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "Create Time")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "Update Time")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "EduTeacher{" +
                "id=" + id +
                ", name=" + name +
                ", intro=" + intro +
                ", career=" + career +
                ", level=" + level +
                ", avatar=" + avatar +
                ", sort=" + sort +
                ", isDeleted=" + isDeleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
