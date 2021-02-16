package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.entity.bo.CourseQuery;
import com.xxLiuxx.eduservice.entity.bo.PublishBo;
import com.xxLiuxx.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getCourseInfo/{courseId}")
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

    @GetMapping("getFinalPublish/{courseId}")
    @ApiOperation(value = "query final publish object by course id")
    public CommonResult getFinalPublishBo(@PathVariable String courseId) {
        PublishBo publishBo = this.courseService.getFinalPublishBo(courseId);
        return CommonResult.success().data("publish", publishBo);
    }

    @PostMapping("publishCourse/{courseId}")
    public CommonResult publishCourse(@PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        this.courseService.updateById(eduCourse);
        return CommonResult.success();
    }

    @PostMapping("queryCourse/{pageNumber}/{limit}")
    @ApiOperation(value = "query course with pagination")
    public CommonResult queryCourse(@PathVariable Long pageNumber,
                                    @PathVariable Long limit,
                                    @RequestBody CourseQuery courseQuery) {
        PageResult<EduCourse> pageResult = this.courseService.queryCourse(pageNumber, limit, courseQuery);
        List<EduCourse> courseList = pageResult.getItems();
        if(CollectionUtils.isEmpty(courseList)) {
            return CommonResult.notFound().message("course not found");
        }
        return CommonResult.success().data("courseList", courseList).data("total", pageResult.totalRows);
    }

    @DeleteMapping("{courseId}")
    public CommonResult deleteCourseById(@PathVariable String courseId) {
        this.courseService.deleteCourse(courseId);
        return CommonResult.success();
    }
}

