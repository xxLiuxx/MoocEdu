package com.xxLiuxx.eduService.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class DemoData {
    @ExcelProperty(value = {"student number"}, index = 0)
    public Integer studentNumber;

    @ExcelProperty(value = {"student name"}, index = 1)
    public String studentName;

    public DemoData() {
    }

    public DemoData(Integer studentNumber, String studentName) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "DemoData{" +
                "studentNumber=" + studentNumber +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
