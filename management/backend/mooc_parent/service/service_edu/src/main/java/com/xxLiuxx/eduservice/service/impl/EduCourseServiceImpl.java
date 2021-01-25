package com.xxLiuxx.eduservice.service.impl;

import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.EduCourseDescription;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.mapper.EduCourseMapper;
import com.xxLiuxx.eduservice.service.EduCourseDescriptionService;
import com.xxLiuxx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.servicebase.handler.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * course service implement
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String addCourse(CourseFormBo courseFormBo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseFormBo, eduCourse);
        boolean flag1 = this.save(eduCourse);
        if(!flag1) {
            throw new MyException(500, "fail to add course");
        }

        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseFormBo.getDescription());
        courseDescription.setId(eduCourse.getId());
        boolean flag2 = this.courseDescriptionService.save(courseDescription);
        if(!flag2) {
            throw new MyException(500, "fail to add course");
        }
        return eduCourse.getId();
    }
}
