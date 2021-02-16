package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.entity.bo.CourseQuery;
import com.xxLiuxx.eduservice.entity.bo.PublishBo;

/**
 * <p>
 * course service
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * add course
     * @param courseFormBo
     */
    String addCourse(CourseFormBo courseFormBo);

    /**
     * query course
     * @param courseId
     * @return
     */
    CourseFormBo queryCourseById(String courseId);

    /**
     * update course
     * @param courseFormBo
     */
    void updateCourseById(CourseFormBo courseFormBo);

    /**
     * get publish object by course id
     * @param courseId
     * @return
     */
    PublishBo getFinalPublishBo(String courseId);

    /**
     * query course with pagination
     * @param pageNumber
     * @param limit
     * @param courseQuery
     * @return
     */
    PageResult<EduCourse> queryCourse(Long pageNumber, Long limit, CourseQuery courseQuery);

    /**
     * delete course
     * @param courseId
     */
    void deleteCourse(String courseId);
}
