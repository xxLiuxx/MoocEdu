package com.xxLiuxx.vodservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Yuchen Liu
 */

@Service
public interface VodService {
    /**
     * upload video by aliyun
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * delete video on aliyun
     * @param videoId
     */
    void deleteVideo(String videoId);

    /**
     * delete multiple video on aliyun
     * @param videoIdList
     */
    void deleteBatch(List<String> videoIdList);

    /**
     * get video auth
     * @param id
     * @return
     */
    String getVodAuth(String id);
}
