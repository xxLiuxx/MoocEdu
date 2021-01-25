package com.xxLiuxx.eduservice.service;

import com.xxLiuxx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;

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
}
