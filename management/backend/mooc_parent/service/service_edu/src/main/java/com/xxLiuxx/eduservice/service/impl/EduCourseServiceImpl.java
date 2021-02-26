package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.client.VodClient;
import com.xxLiuxx.eduservice.entity.EduCourse;
import com.xxLiuxx.eduservice.entity.EduCourseDescription;
import com.xxLiuxx.eduservice.entity.bo.CourseFormBo;
import com.xxLiuxx.eduservice.entity.bo.CourseQuery;
import com.xxLiuxx.eduservice.entity.bo.PublishBo;
import com.xxLiuxx.eduservice.mapper.EduCourseMapper;
import com.xxLiuxx.eduservice.service.EduChapterService;
import com.xxLiuxx.eduservice.service.EduCourseDescriptionService;
import com.xxLiuxx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxLiuxx.eduservice.service.EduVideoService;
import com.xxLiuxx.servicebase.handler.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private EduVideoService videoService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private VodClient vodClient;

    @Override
    public String addCourse(CourseFormBo courseFormBo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseFormBo, eduCourse);
        boolean flag1 = this.save(eduCourse);
        if (!flag1) {
            throw new MyException(500, "fail to add course");
        }

        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseFormBo.getDescription());
        courseDescription.setId(eduCourse.getId());
        boolean flag2 = this.courseDescriptionService.save(courseDescription);
        if (!flag2) {
            throw new MyException(500, "fail to add course");
        }
        return eduCourse.getId();
    }

    @Override
    public CourseFormBo queryCourseById(String courseId) {
        // get course
        EduCourse course = this.getById(courseId);

        CourseFormBo courseFormBo = new CourseFormBo();

        BeanUtils.copyProperties(course, courseFormBo);

        // get description
        EduCourseDescription courseDescription = this.courseDescriptionService.getById(courseId);
        courseFormBo.setDescription(courseDescription.getDescription());

        return courseFormBo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseById(CourseFormBo courseFormBo) {
        // update the course
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseFormBo, course);
        boolean flag = this.updateById(course);
        if (!flag) {
            throw new MyException(500, "fail to save course");
        }

        // update the description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseFormBo.getId());
        courseDescription.setDescription(courseFormBo.getDescription());
        boolean flag2 = this.courseDescriptionService.updateById(courseDescription);
        if (!flag2) {
            throw new MyException(500, "fail to save description");
        }
    }

    @Override
    public PublishBo getFinalPublishBo(String courseId) {
        PublishBo finalPublishBo = this.baseMapper.getFinalPublishBo(courseId);
        return finalPublishBo;
    }

    @Override
    public PageResult<EduCourse> queryCourse(Long pageNumber, Long limit, CourseQuery courseQuery) {
        Page<EduCourse> coursePage = new Page<>(pageNumber, limit);
        if (courseQuery == null) {
            this.page(coursePage, null);
            return new PageResult<EduCourse>(coursePage.getCurrent(), coursePage.getTotal(), coursePage.getRecords());
        }
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<EduCourse>();

        wrapper.orderByDesc("gmt_create");

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (StringUtils.isNotBlank(title)) {
            wrapper.like("title", title);
        }

        if (StringUtils.isNotBlank(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }

        if(StringUtils.isNotBlank(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }

        if(StringUtils.isNotBlank(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }

        this.page(coursePage, wrapper);
        return new PageResult<>(coursePage.getCurrent(), coursePage.getTotal(), coursePage.getRecords());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCourse(String courseId) {
        // delete video
        this.videoService.deleteVideoByCourseId(courseId);


        // delete chapter
        this.chapterService.deleteChapterByCourseId(courseId);

        // delete description
        this.courseDescriptionService.removeById(courseId);

        // delete course
        boolean flag = this.removeById(courseId);

        if(!flag) {
            throw new MyException(500, "fail to delete course");
        }
    }

    @Override
    public List<EduCourse> queryCourseWithLimit() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 8");
        List<EduCourse> courseList = this.list(wrapper);

        return courseList;
    }


}
