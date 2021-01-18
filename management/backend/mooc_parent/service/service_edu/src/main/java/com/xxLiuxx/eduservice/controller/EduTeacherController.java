package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.commonutils.entity.PageResult;
import com.xxLiuxx.eduservice.entity.EduTeacher;
import com.xxLiuxx.eduservice.entity.vo.TeacherQuery;
import com.xxLiuxx.eduservice.service.EduTeacherService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Teacher Controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-12
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = {"Teacher Management Interface"})
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    @GetMapping("findAll")
    @ApiOperation(value = "find all teachers")
    public CommonResult findAll() {
        List<EduTeacher> teacherList = this.eduTeacherService.list(null);
        if (CollectionUtils.isEmpty(teacherList)) {
            return CommonResult.notFound();
        }
        return CommonResult.success().data("list", teacherList);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "query teacher by id")
    public CommonResult queryTeacherById(@PathVariable String id) {
        EduTeacher teacher = this.eduTeacherService.getById(id);

        return teacher != null ? CommonResult.success().data("teacher", teacher) : CommonResult.notFound();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "logic delete teacher by id")
    public CommonResult deleteTeacherById(@PathVariable String id) {
        boolean flag = this.eduTeacherService.removeById(id);

        return flag ? CommonResult.success() : CommonResult.notFound().message("the id doesn't exist");
    }

    @PutMapping("{id}")
    @ApiOperation(value = "update teacher")
    public CommonResult updateTeacherById(@PathVariable String id, @RequestBody EduTeacher eduTeacher) {
        eduTeacher.setId(id);
        boolean flag = this.eduTeacherService.updateById(eduTeacher);

        return flag ? CommonResult.success() : CommonResult.notFound().message("the id doesn't exist");
    }

    @PostMapping("pageTeacher/{pageNumber}/{limit}")
    @ApiOperation(value = "query teachers by page")
    public CommonResult queryTeacherByPage(@PathVariable("pageNumber") Long pageNumber,
                                           @PathVariable("limit") Long limit,
                                           @RequestBody(required = false) TeacherQuery teacherQuery) {
        PageResult<EduTeacher> pageResult = this.eduTeacherService.queryTeacherByPage(pageNumber, limit, teacherQuery);
        List<EduTeacher> items = pageResult.getItems();
        if(CollectionUtils.isEmpty(items)) {
            return CommonResult.notFound();
        }
        return CommonResult.success().data("pageResult", pageResult);
    }

    @PostMapping("addTeacher")
    @ApiOperation(value = "add teacher")
    public CommonResult addTeacher(@RequestBody EduTeacher teacher) {
        boolean flag = this.eduTeacherService.save(teacher);
        return flag ? CommonResult.success() : CommonResult.error().message("fail to add teacher");
    }
}

