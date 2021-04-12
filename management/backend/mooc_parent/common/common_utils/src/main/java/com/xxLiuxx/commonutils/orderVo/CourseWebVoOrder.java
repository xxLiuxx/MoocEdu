package com.xxLiuxx.commonutils.orderVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Yuchen Liu
 */
@ApiModel(value="course base info", description="front course detail")
public class CourseWebVoOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "course title")
    private String title;

    @ApiModelProperty(value = "price")
    private BigDecimal price;

    @ApiModelProperty(value = "lesson num")
    private Integer lessonNum;

    @ApiModelProperty(value = "course cover")
    private String cover;

    @ApiModelProperty(value = "buy count")
    private Long buyCount;

    @ApiModelProperty(value = "total view")
    private Long viewCount;

    @ApiModelProperty(value = "course description")
    private String description;

    @ApiModelProperty(value = "teacher id")
    private String teacherId;

    @ApiModelProperty(value = "teacher name")
    private String teacherName;

    @ApiModelProperty(value = "teacher intro")
    private String intro;

    @ApiModelProperty(value = "teacher avatar")
    private String avatar;

    @ApiModelProperty(value = "course subject one id")
    private String subjectLevelOneId;

    @ApiModelProperty(value = "course subject one name")
    private String subjectLevelOne;

    @ApiModelProperty(value = "course subject two id")
    private String subjectLevelTwoId;

    @ApiModelProperty(value = "course subject two name")
    private String subjectLevelTwo;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSubjectLevelOneId() {
        return subjectLevelOneId;
    }

    public void setSubjectLevelOneId(String subjectLevelOneId) {
        this.subjectLevelOneId = subjectLevelOneId;
    }

    public String getSubjectLevelOne() {
        return subjectLevelOne;
    }

    public void setSubjectLevelOne(String subjectLevelOne) {
        this.subjectLevelOne = subjectLevelOne;
    }

    public String getSubjectLevelTwoId() {
        return subjectLevelTwoId;
    }

    public void setSubjectLevelTwoId(String subjectLevelTwoId) {
        this.subjectLevelTwoId = subjectLevelTwoId;
    }

    public String getSubjectLevelTwo() {
        return subjectLevelTwo;
    }

    public void setSubjectLevelTwo(String subjectLevelTwo) {
        this.subjectLevelTwo = subjectLevelTwo;
    }
}
