package com.xxLiuxx.ossservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuchen Liu
 */
@Service
public interface OssService {
    /**
     * upload avatar with Aliyun OSS
     * @param file
     * @return
     */
    String fileUpload(MultipartFile file);
}
