package com.xxLiuxx.eduservice.controller.front;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.frontVo.CourseFrontVo;
import com.xxLiuxx.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yuchen Liu
 */
@RestController
@RequestMapping("/eduservice/front/course")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@RequestBody(required = false) CourseFrontVo courseQuery,
                                           @PathVariable("page") long page,
                                           @PathVariable("limit") long limit) {
        PageResult<EduCourse> frontCoursePage = this.courseService.getFrontCourseList(courseQuery, page, limit);
        return CommonResult.success().data("courseList", frontCoursePage);

    }
}
