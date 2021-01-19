package com.xxLiuxx.ossservice.controller;

import com.xxLiuxx.commonutils.entity.CommonResult;
import com.xxLiuxx.ossservice.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuchen Liu
 */

@RestController
@RequestMapping("eduoss/file")
@Api(tags = {"OSS file uploader controller"})
public class OssController {
    @Autowired
    private OssService ossService;

    @PostMapping
    public CommonResult fileUpload(MultipartFile file) {
        String url = this.ossService.fileUpload(file);
        return CommonResult.success().data("url", url);
    }
}
