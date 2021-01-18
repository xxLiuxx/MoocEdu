package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduTeacher;
import com.xxLiuxx.eduservice.entity.vo.TeacherQuery;
import com.xxLiuxx.eduservice.mapper.EduTeacherMapper;
import com.xxLiuxx.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Teacher ServiceImpl
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
     * query teacher by page
     * @param pageNumber
     * @param limit
     * @return PageResult
     */
    @Override
    public PageResult<EduTeacher> queryTeacherByPage(Long pageNumber, Long limit, TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(pageNumber, limit);
        if(teacherQuery == null) {
            this.page(teacherPage, null);
            return new PageResult<>(teacherPage.getCurrent(), teacherPage.getTotal(), teacherPage.getRecords());
        }

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }

        if(level != null) {
            wrapper.eq("level", level);
        }

        if(StringUtils.isNotBlank(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if(StringUtils.isNotBlank(end)) {
            wrapper.le("gmt_create", end);
        }

        this.page(teacherPage, wrapper);

        return new PageResult<>(teacherPage.getCurrent(), teacherPage.getTotal(), teacherPage.getRecords());
    }

}
