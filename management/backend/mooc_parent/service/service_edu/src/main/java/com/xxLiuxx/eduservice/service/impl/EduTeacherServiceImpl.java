package com.xxLiuxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduTeacher;
import com.xxLiuxx.eduservice.entity.bo.TeacherQuery;
import com.xxLiuxx.eduservice.mapper.EduTeacherMapper;
import com.xxLiuxx.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

        wrapper.orderByDesc("gmt_create");

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

    @Cacheable(value = "teacher", key = "'selectTeacherList'")
    @Override
    public List<EduTeacher> queryTeacherWithLimit() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 4");
        List<EduTeacher> teacherList = this.list(wrapper);

        return teacherList;
    }

    @Override
    public PageResult<EduTeacher> getFrontTeacherList(Long page, Long limit) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");

        Page<EduTeacher> eduTeacherPage = new Page<>(page, limit);

        this.baseMapper.selectPage(eduTeacherPage, wrapper);

        List<EduTeacher> records = eduTeacherPage.getRecords();
        long current = eduTeacherPage.getCurrent();
        long pages = eduTeacherPage.getPages();
        long size = eduTeacherPage.getSize();
        long total = eduTeacherPage.getTotal();
        boolean hasNext = eduTeacherPage.hasNext();
        boolean hasPrevious = eduTeacherPage.hasPrevious();

        return new PageResult<>(current, total, records, hasNext, hasPrevious, size, pages);
    }

}
