package com.xxLiuxx.statservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * daily statistics
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-08-29
 */
@ApiModel(value="StatisticsDaily", description="daily statistics")
public class StatisticsDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "PRIMARY")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "DATE")
    private String dateCalculated;

    @ApiModelProperty(value = "Register number")
    private Integer registerNum;

    @ApiModelProperty(value = "Login number")
    private Integer loginNum;

    @ApiModelProperty(value = "daily view count")
    private Integer videoViewNum;

    @ApiModelProperty(value = "daily number of course added")
    private Integer courseNum;

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

    public String getDateCalculated() {
        return dateCalculated;
    }

    public void setDateCalculated(String dateCalculated) {
        this.dateCalculated = dateCalculated;
    }

    public Integer getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Integer getVideoViewNum() {
        return videoViewNum;
    }

    public void setVideoViewNum(Integer videoViewNum) {
        this.videoViewNum = videoViewNum;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
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
        return "StatisticsDaily{" +
        "id=" + id +
        ", dateCalculated=" + dateCalculated +
        ", registerNum=" + registerNum +
        ", loginNum=" + loginNum +
        ", videoViewNum=" + videoViewNum +
        ", courseNum=" + courseNum +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
