package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * course controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourse")
    public CommonResult addCourse(@RequestBody CourseFormBo courseFormBo) {
        String courseId = this.courseService.addCourse(courseFormBo);
        return CommonResult.success().data("courseId", courseId);
    }
}

