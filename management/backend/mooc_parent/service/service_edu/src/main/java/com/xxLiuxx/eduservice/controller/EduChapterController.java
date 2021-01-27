package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.bo.ChapterBo;
import com.xxLiuxx.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * chapter controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
@Api(tags = {"chapter controller"})
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;


    @GetMapping("getChapterVideo/{courseId}")
    @ApiOperation(value = "find the chapter with the course id, and find the corresponding video under this chapter")
    public CommonResult getChapterVideo(@PathVariable String courseId) {
        List<ChapterBo> chapterList = this.chapterService.getChapterVideo(courseId);
        if (CollectionUtils.isEmpty(chapterList)) {
            return CommonResult.notFound().message("chapter not found");
        }
        return CommonResult.success().data("chapterList", chapterList);
    }
}

