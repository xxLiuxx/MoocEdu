package com.xxLiuxx.orderservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * order
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-04-12
 */
@ApiModel(value="EduOrder", description="order")
public class EduOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "order number")
    private String orderNo;

    @ApiModelProperty(value = "course id")
    private String courseId;

    @ApiModelProperty(value = "course name")
    private String courseTitle;

    @ApiModelProperty(value = "course cover")
    private String courseCover;

    @ApiModelProperty(value = "teacher name")
    private String teacherName;

    @ApiModelProperty(value = "member id")
    private String memberId;

    @ApiModelProperty(value = "nickname")
    private String nickname;

    @ApiModelProperty(value = "mobile")
    private String mobile;

    @ApiModelProperty(value = "total fee")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "payment type（1：WeChat 2：Alipay")
    private Integer payType;

    @ApiModelProperty(value = "order status（0：not paid 1：paid)")
    private Integer status;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
        return "EduOrder{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", courseId=" + courseId +
        ", courseTitle=" + courseTitle +
        ", courseCover=" + courseCover +
        ", teacherName=" + teacherName +
        ", memberId=" + memberId +
        ", nickname=" + nickname +
        ", mobile=" + mobile +
        ", totalFee=" + totalFee +
        ", payType=" + payType +
        ", status=" + status +
        ", isDeleted=" + isDeleted +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
