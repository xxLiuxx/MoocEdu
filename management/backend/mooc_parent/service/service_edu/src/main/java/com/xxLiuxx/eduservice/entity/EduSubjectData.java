package com.xxLiuxx.eduservice.entity;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author Yuchen Liu
 */
public class EduSubjectData {
    @ExcelProperty(index = 0)
    public String subjectOneTitle;

    @ExcelProperty(index = 1)
    public String subjectTwoTitle;

    public String getSubjectOneTitle() {
        return subjectOneTitle;
    }

    public void setSubjectOneTitle(String subjectOneTitle) {
        this.subjectOneTitle = subjectOneTitle;
    }

    public String getSubjectTwoTitle() {
        return subjectTwoTitle;
    }

    public void setSubjectTwoTitle(String subjectTwoTitle) {
        this.subjectTwoTitle = subjectTwoTitle;
    }
}
