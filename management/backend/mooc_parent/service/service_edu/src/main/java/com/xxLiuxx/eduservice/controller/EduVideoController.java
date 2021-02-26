package com.xxLiuxx.eduservice.controller;


import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.eduservice.entity.EduVideo;
import com.xxLiuxx.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * video controller
 * </p>
 *
 * @author Yuchen Liu
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @GetMapping("{videoId}")
    public CommonResult getVideoById(@PathVariable String videoId) {
        EduVideo video = this.videoService.getById(videoId);
        if (video == null) {
            return CommonResult.notFound().message("video not found");
        }
        return CommonResult.success().data("video", video);
    }

    @PostMapping("addVideo")
    public CommonResult addVideo(@RequestBody EduVideo video) {
        this.videoService.save(video);
        return CommonResult.success().message("video saved");
    }


    @DeleteMapping("{videoId}")
    public CommonResult deleteVideo(@PathVariable String videoId) {
        this.videoService.removeVideo(videoId);
        return CommonResult.success().message("video deleted");
    }

    @PutMapping("updateVideo")
    public CommonResult updateVideo(@RequestBody EduVideo video) {
        boolean flag = this.videoService.updateById(video);
        if (!flag) {
            return CommonResult.error().message("fail to update video");
        }
        return CommonResult.success().message("video updated");
    }
}

