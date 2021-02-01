package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.EduChapter;
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

    @PostMapping("addChapter")
    @ApiOperation(value = "add chapter")
    public CommonResult addChapter(@RequestBody EduChapter chapter) {
        boolean save = this.chapterService.save(chapter);
        if (!save) {
            return CommonResult.error().message("fail to add chapter");
        }
        return CommonResult.success().message("chapter added");
    }

    @GetMapping("{chapterId}")
    @ApiOperation(value = "get chapter info by chapter id")
    public CommonResult queryChapterById(@PathVariable String chapterId) {
        EduChapter chapter = this.chapterService.getById(chapterId);
        if (chapter == null) {
            return CommonResult.notFound().message("chapter not found");
        }
        return CommonResult.success().data("chapter", chapter);
    }

    @PutMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody EduChapter chapter) {
        boolean flag = this.chapterService.updateById(chapter);
        if (!flag) {
            return CommonResult.error().message("fail to update chapter");
        }
        return CommonResult.success().message("chapter updated");
    }

    @DeleteMapping("{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {
        boolean result = this.chapterService.deleteChapter(chapterId);
        if (!result) {
            return CommonResult.error();
        }
        return CommonResult.success();
    }
}

