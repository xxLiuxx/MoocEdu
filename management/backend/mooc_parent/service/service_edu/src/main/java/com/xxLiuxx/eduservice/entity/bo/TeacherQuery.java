package com.xxLiuxx.eduservice.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Yuchen Liu
 */

@ApiModel("query object with conditions for teacher")
public class TeacherQuery implements Serializable{

    private static final long serialVersionUID = 2L;

    @ApiModelProperty(value = "teacher name, flexible query")
    private String name;

    @ApiModelProperty(value = "teacher level")
    private Integer level;

    @ApiModelProperty(value = "begin time", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "end time", example = "2019-12-01 10:10:10")
    private String end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
