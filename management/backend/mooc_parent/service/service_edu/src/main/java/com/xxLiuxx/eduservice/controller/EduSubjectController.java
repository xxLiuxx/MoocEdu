package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.EduSubject;
import com.xxLiuxx.eduservice.entity.bo.SubjectBo;
import com.xxLiuxx.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * subject controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(tags = {"subject controller"})
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @PostMapping("import")
    @ApiParam(value = "read data from .xlsx file and save them in the database")
    public CommonResult importSubject(MultipartFile file) {
        this.subjectService.importSubject(file);
        return CommonResult.success();
    }

    @GetMapping("findAll")
    @ApiOperation(value = "find all subjects in tree structure")
    public CommonResult findAllSubject() {
        List<SubjectBo> tree = this.subjectService.findAllSubject();
        if(CollectionUtils.isEmpty(tree)) {
            return CommonResult.notFound().message("the subject list is empty");
        }
        return CommonResult.success().data("subjectTree", tree);
    }
}

