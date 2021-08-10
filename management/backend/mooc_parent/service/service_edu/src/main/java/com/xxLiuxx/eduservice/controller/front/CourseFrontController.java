package com.xxLiuxx.eduservice.controller.front;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.commonutils.orderVo.CourseWebVoOrder;
import com.xxLiuxx.commonutils.utils.JwtUtils;
import com.xxLiuxx.eduservice.client.OrderClient;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;
import com.xxLiuxx.eduservice.entity.frontVo.CourseFrontVo;
import com.xxLiuxx.eduservice.entity.frontVo.CourseWebVo;
import com.xxLiuxx.eduservice.service.EduChapterService;
import com.xxLiuxx.eduservice.service.EduCourseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private OrderClient orderClient;

    @PostMapping("getFrontCourseList/{page}/{limit}")
    public CommonResult getFrontCourseList(@RequestBody(required = false) CourseFrontVo courseQuery,
                                           @PathVariable("page") long page,
                                           @PathVariable("limit") long limit) {
        PageResult<EduCourse> frontCoursePage = this.courseService.getFrontCourseList(courseQuery, page, limit);
        return CommonResult.success().data("courseList", frontCoursePage);

    }

    @GetMapping("getFrontCourse/{courseId}")
    public CommonResult getBaseCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        // get base info (course, teacher...)
        CourseWebVo course = this.courseService.getBaseCourseInfo(courseId);

        // get chapter and section under this course
        List<ChapterBo> chapterVideo = this.chapterService.getChapterVideo(courseId);

        // check if the user has bought the course
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean isBought = this.orderClient.checkCourseStatus(courseId, memberId);

        return CommonResult.success().data("course", course).data("chapterVideo", chapterVideo).data("isBought", isBought);
    }

    @GetMapping("getCourseInfoForOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoForOrder(@PathVariable String courseId) {
        CourseWebVo course = this.courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(course, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
