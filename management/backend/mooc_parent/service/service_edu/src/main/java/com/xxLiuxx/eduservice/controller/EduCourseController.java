package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "course controller")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("addCourse")
    @ApiOperation(value = "add course")
    public CommonResult addCourse(@RequestBody CourseFormBo courseFormBo) {
        String courseId = this.courseService.addCourse(courseFormBo);
        return CommonResult.success().data("courseId", courseId);
    }

    @GetMapping("{courseId}")
    @ApiOperation(value = "query course and description by course if")
    public CommonResult queryCourseById(@PathVariable String courseId) {
        CourseFormBo courseInfo = this.courseService.queryCourseById(courseId);
        return CommonResult.success().data("courseInfo", courseInfo);
    }

    @PutMapping("updateCourse")
    public CommonResult updateCourseById(@RequestBody CourseFormBo courseFormBo) {
        this.courseService.updateCourseById(courseFormBo);
        return CommonResult.success();
    }
}

