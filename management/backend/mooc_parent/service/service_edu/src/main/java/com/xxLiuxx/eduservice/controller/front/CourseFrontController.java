package com.xxLiuxx.eduservice.controller.front;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;
import com.xxLiuxx.eduservice.entity.frontVo.CourseFrontVo;
import com.xxLiuxx.eduservice.entity.frontVo.CourseWebVo;
import com.xxLiuxx.eduservice.service.EduChapterService;
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

    @Autowired
    private EduChapterService chapterService;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@RequestBody(required = false) CourseFrontVo courseQuery,
                                           @PathVariable("page") long page,
                                           @PathVariable("limit") long limit) {
        PageResult<EduCourse> frontCoursePage = this.courseService.getFrontCourseList(courseQuery, page, limit);
        return CommonResult.success().data("courseList", frontCoursePage);

    }

    @GetMapping("getFrontCourse/{id}")
    public CommonResult getBaseCourseInfo(@PathVariable String id) {
        // get base info (course, teacher...)
        CourseWebVo course = this.courseService.getBaseCourseInfo(id);

        // get chapter and section under this course
        List<ChapterBo> chapterVideo = this.chapterService.getChapterVideo(id);

        return CommonResult.success().data("course", course).data("chapterVideo", chapterVideo);
    }
}
