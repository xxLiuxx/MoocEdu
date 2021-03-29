package com.xxLiuxx.eduservice.mapper;

import com.xxLiuxx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxLiuxx.eduservice.entity.bo.PublishBo;
import com.xxLiuxx.eduservice.entity.frontVo.CourseWebVo;

/**
 * <p>
 * course Mapper interface
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    PublishBo getFinalPublishBo(String courseId);

    CourseWebVo getCourseBaseInfo(String courseId);
}
