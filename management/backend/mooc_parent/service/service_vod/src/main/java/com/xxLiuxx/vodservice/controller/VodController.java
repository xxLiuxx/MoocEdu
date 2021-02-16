package com.xxLiuxx.vodservice.controller;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.vodservice.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Yuchen Liu
 */

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Api(tags = {"video upload controller"})
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public CommonResult uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return CommonResult.success().data("videoId", videoId);
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public CommonResult deleteVideo(@PathVariable String videoId) {
        this.vodService.deleteVideo(videoId);
        return CommonResult.success();
    }

    @DeleteMapping("deleteBatch")
    public CommonResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        this.vodService.deleteBatch(videoIdList);
        return CommonResult.success();
    }
}
