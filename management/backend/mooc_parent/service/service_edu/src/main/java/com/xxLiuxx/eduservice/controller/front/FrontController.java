package com.xxLiuxx.eduservice.controller.front;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.EduTeacher;
import com.xxLiuxx.eduservice.service.EduCourseService;
import com.xxLiuxx.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("/eduservice/front")
@CrossOrigin
@Api(tags = {"controller for front portal"})
public class FrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("queryTeacherAndCourse")
    public CommonResult getTeacherAndCourseWithLimit() {
        List<EduTeacher> teacherList = this.teacherService.queryTeacherWithLimit();
        List<EduCourse> courseList = this.courseService.queryCourseWithLimit();

        return CommonResult.success().data("teacherList", teacherList).data("courseList", courseList);
    }

    @GetMapping("queryTeacher/{id}")
    public CommonResult queryTeacherById(@PathVariable String id) {
        EduTeacher teacher = this.teacherService.getById(id);
        return CommonResult.success().data("teacher", teacher);
    }
}
