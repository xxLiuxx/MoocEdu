package com.xxLiuxx.eduservice.controller.front;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.EduTeacher;
import com.xxLiuxx.eduservice.service.EduCourseService;
import com.xxLiuxx.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("/eduservice/front/teacher")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @PostMapping("getFrontTeacherList/{page}/{limit}")
    public CommonResult getFrontTeacherList(@PathVariable("page") Long page, @PathVariable("limit")Long limit) {
        PageResult<EduTeacher> frontTeacherPage = this.teacherService.getFrontTeacherList(page, limit);
        return CommonResult.success().data("teacherList", frontTeacherPage);
    }

    @GetMapping("getFrontTeacher/{id}")
    public CommonResult getFrontTeacher(@PathVariable String id) {
        // get teacher by id
        EduTeacher teacher = this.teacherService.getById(id);
        if(teacher == null) {
            return CommonResult.notFound().message("teacher not found");
        }
        // get all the courses taught by teacher
        List<EduCourse> courseList = this.courseService.getCoursesByTeacherId(id);
        return CommonResult.success().data("teacher", teacher).data("courseList", courseList);
    }
}
